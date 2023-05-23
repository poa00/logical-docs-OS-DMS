package com.logicaldoc.gui.frontend.client.reports;

import com.logicaldoc.gui.common.client.Feature;
import com.logicaldoc.gui.common.client.i18n.I18N;
import com.logicaldoc.gui.common.client.util.GridUtil;
import com.logicaldoc.gui.common.client.util.ItemFactory;
import com.logicaldoc.gui.common.client.widgets.InfoPanel;
import com.logicaldoc.gui.common.client.widgets.grid.RefreshableListGrid;
import com.logicaldoc.gui.frontend.client.administration.AdminPanel;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * An abstract panel for standard reports
 * 
 * @author Marco Meschieri - LogicalDOC
 * @since 8.8.3
 */
abstract class ReportPanel extends AdminPanel {

	protected RefreshableListGrid list;

	protected String infoLabel;

	protected ReportPanel(String title, String infoLabel) {
		super(title);
		this.infoLabel = infoLabel;
		this.list = createListGrid();
	}

	@Override
	public void onDraw() {
		ToolStrip toolStrip = prepareToolBar();

		list.setEmptyMessage(I18N.message("notitemstoshow"));
		list.setShowRecordComponents(true);
		list.setShowRecordComponentsByCell(true);
		list.setCanFreezeFields(false);
		list.setAutoFetchData(true);
		list.setFilterOnKeypress(true);
		list.setSelectionType(SelectionStyle.MULTIPLE);
		list.setShowFilterEditor(true);

		list.addCellContextClickHandler(event -> {
			showContextMenu();
			event.cancel();
		});

		final InfoPanel infoPanel = new InfoPanel("");
		list.addDataArrivedHandler(
				event -> infoPanel.setMessage(I18N.message(infoLabel, Integer.toString(list.getTotalRows()))));

		prepareListGrid();
		body.setMembers(toolStrip, infoPanel, list);

		refresh();
	}

	protected RefreshableListGrid createListGrid() {
		return new RefreshableListGrid() {
			@Override
			protected String getCellCSSText(ListGridRecord rec, int rowNum, int colNum) {
				if (getFieldName(colNum).equals("filename")) {
					if ("stop".equals(rec.getAttribute("immutable"))) {
						return "color: #888888; font-style: italic;";
					} else {
						return super.getCellCSSText(rec, rowNum, colNum);
					}
				} else {
					return super.getCellCSSText(rec, rowNum, colNum);
				}
			}
		};
	}

	protected abstract void prepareListGrid();

	private ToolStrip prepareToolBar() {
		ToolStrip toolStrip = new ToolStrip();
		toolStrip.setHeight(20);
		toolStrip.setWidth100();
		toolStrip.addSpacer(2);

		fillToolBar(toolStrip);

		ToolStripButton print = new ToolStripButton();
		print.setIcon(ItemFactory.newImgIcon("printer.png").getSrc());
		print.setTooltip(I18N.message("print"));
		print.setAutoFit(true);
		print.addClickHandler(event -> GridUtil.print(list));
		toolStrip.addSeparator();
		toolStrip.addButton(print);

		if (Feature.visible(Feature.EXPORT_CSV)) {
			toolStrip.addSeparator();
			ToolStripButton export = new ToolStripButton();
			export.setIcon(ItemFactory.newImgIcon("table_row_insert.png").getSrc());
			export.setTooltip(I18N.message("export"));
			export.setAutoFit(true);
			toolStrip.addButton(export);
			export.addClickHandler(event -> GridUtil.exportCSV(list, false));
			if (!Feature.enabled(Feature.EXPORT_CSV)) {
				export.setDisabled(true);
				export.setTooltip(I18N.message("featuredisabled"));
			}
		}

		toolStrip.addFill();
		return toolStrip;
	}

	protected void fillToolBar(ToolStrip toolStrip) {
		// Nothing to do
	}

	protected abstract void showContextMenu();

	protected abstract void refresh();
}