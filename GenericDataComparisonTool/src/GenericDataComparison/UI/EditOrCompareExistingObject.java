package GenericDataComparison.UI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EditOrCompareExistingObject {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditOrCompareExistingObject window = new EditOrCompareExistingObject();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(759, 456);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.WRAP);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setBounds(10, 98, 723, 30);
		lblNewLabel.setText("Select an object for comparison. Or, click [placeholder] to edit baseline object.");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(347, 382, 75, 25);
		btnNewButton.setText("Back");
		
		Label label = new Label(shell, SWT.WRAP);
		label.setText("Edit Or Compare Existing Object");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(0, 10, 723, 72);
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setImage(SWTResourceManager.getImage(EditOrCompareExistingObject.class, "/GenericDataComparison/UI/img/Delete.png"));
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDelete.setText("Delete");
		btnDelete.setBounds(347, 173, 75, 25);
		
		Button btnEdit = new Button(shell, SWT.NONE);
		btnEdit.setImage(SWTResourceManager.getImage(EditOrCompareExistingObject.class, "/GenericDataComparison/UI/img/List.png"));
		btnEdit.setText("Edit");
		btnEdit.setBounds(253, 173, 75, 25);
		
		Button btnCar = new Button(shell, SWT.NONE);
		btnCar.setText("Car");
		btnCar.setBounds(441, 173, 75, 25);

	}
}
