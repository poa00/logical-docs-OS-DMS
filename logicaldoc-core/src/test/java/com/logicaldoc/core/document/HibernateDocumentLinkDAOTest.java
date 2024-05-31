package com.logicaldoc.core.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.logicaldoc.core.AbstractCoreTestCase;
import com.logicaldoc.core.PersistenceException;
import com.logicaldoc.util.plugin.PluginException;

/**
 * Test case for <code>HibernateDocumentLinkDAO</code>
 * 
 * @author Matteo Caruso - LogicalDOC
 * @since 4.0
 */
public class HibernateDocumentLinkDAOTest extends AbstractCoreTestCase {

	// Instance under test
	private DocumentLinkDAO dao;

	private DocumentDAO docDao;

	@Before
	public void setUp() throws FileNotFoundException, IOException, SQLException, PluginException {
		super.setUp();
		// Retrieve the instance under test from spring context. Make sure that
		// it is an HibernateDocumentLinkDAO
		dao = (DocumentLinkDAO) context.getBean("DocumentLinkDAO");

		docDao = (DocumentDAO) context.getBean("DocumentDAO");
	}

	@Test
	public void testStore() throws PersistenceException {
		DocumentLink link = new DocumentLink();
		Document doc1 = docDao.findById(1);
		Document doc2 = docDao.findById(2);
		link.setDocument1(doc1);
		link.setDocument2(doc2);
		link.setType("zzz");
		dao.store(link);
		link = dao.findById(link.getId());
		assertEquals(1, link.getDocument1().getId());
		assertEquals(2, link.getDocument2().getId());
		assertEquals("zzz", link.getType());
	}

	@Test
	public void testDelete() throws PersistenceException {
		DocumentLink link = dao.findById(1);
		assertNotNull(link);
		dao.delete(1);
		link = dao.findById(1);
		assertNull(link);

		// Try with unexisting link
		dao.delete(99);

		link = dao.findById(1);
		assertNull(link);
		link = dao.findById(2);
		assertNotNull(link);
	}

	@Test
	public void testFindById() throws PersistenceException {
		DocumentLink link1 = dao.findById(1);
		assertNotNull(link1);
		assertEquals(1, link1.getDocument1().getId());
		assertEquals(3, link1.getDocument2().getId());
		assertEquals("test", link1.getType());

		DocumentLink link2 = dao.findById(2);
		assertNotNull(link2);
		assertEquals(3, link2.getDocument1().getId());
		assertEquals(1, link2.getDocument2().getId());

		link2 = dao.findById(99);
		assertNull(link2);
	}

	@Test
	public void testFindByDocId() throws PersistenceException {
		Collection<DocumentLink> links = dao.findByDocId(1, null);
		assertNotNull(links);
		assertEquals(4, links.size());

		links = dao.findByDocId(99, null);
		assertNotNull(links);
		assertTrue(links.isEmpty());

		links = dao.findByDocId(1, "test");
		assertNotNull(links);
		assertEquals(1, links.size());

		links = dao.findByDocId(99, "pippo");
		assertNotNull(links);
		assertTrue(links.isEmpty());

		links = dao.findByDocId(1, "pippo");
		assertNotNull(links);
		assertTrue(links.isEmpty());
	}

	@Test
	public void testFindByDocIdsAndType() throws PersistenceException {
		DocumentLink link = dao.findByDocIdsAndType(1, 3, "test");
		assertNotNull(link);
		assertEquals(1, link.getId());

		link = dao.findByDocIdsAndType(3L, 1L, "xyz");
		assertNotNull(link);
		assertEquals(2, link.getId());

		link = dao.findByDocIdsAndType(1L, 3L, "xxx");
		assertNotNull(link);
		assertEquals(3, link.getId());

		link = dao.findByDocIdsAndType(3, 1, "");
		assertNotNull(link);
		assertEquals(4, link.getId());

		link = dao.findByDocIdsAndType(3, 1, null);
		assertNull(link);

		link = dao.findByDocIdsAndType(1, 2, "zzz");
		assertNull(link);

	}
}