package GenericDataComparison.UI;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import GenericDataComparison.Characteristic;
import GenericDataComparison.GenericComparisonManager;
import GenericDataComparison.ObjectType;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;

public class EditOrCompareExistingObject {

	private ArrayList<Button> objectTypeButtons;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		EditOrCompareExistingObject window = new EditOrCompareExistingObject();
		window.open();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		
		// Window properites
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(512, 324);
		shell.setText("Edit Or Compare Existing Object");
		shell.setLayout(new FormLayout());
		
		// UI Header
		Label lblEditOrCompare = new Label(shell, SWT.NONE);
		FormData fd_lblEditOrCompare = new FormData();
		fd_lblEditOrCompare.bottom = new FormAttachment(0, 59);
		fd_lblEditOrCompare.right = new FormAttachment(0, 777);
		fd_lblEditOrCompare.top = new FormAttachment(0, 10);
		fd_lblEditOrCompare.left = new FormAttachment(0, 10);
		lblEditOrCompare.setLayoutData(fd_lblEditOrCompare);
		lblEditOrCompare.setFont(SWTResourceManager.getFont("Segoe UI", 24, SWT.NORMAL));
		lblEditOrCompare.setText("Edit or Compare Existing Object");
		
		// Back Button
		Button btnBack = new Button(shell, SWT.NONE);
		FormData fd_btnBack = new FormData();
		fd_btnBack.bottom = new FormAttachment(100, -10);
		fd_btnBack.left = new FormAttachment(0, 26);
		btnBack.setLayoutData(fd_btnBack);
		btnBack.setText("Back");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.bottom = new FormAttachment(lblEditOrCompare, 163, SWT.BOTTOM);
		fd_scrolledComposite.top = new FormAttachment(lblEditOrCompare, 25);
		fd_scrolledComposite.left = new FormAttachment(btnBack, 3, SWT.LEFT);
		fd_scrolledComposite.right = new FormAttachment(100, -23);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);		
		
		// Setup a group to put all of the buttons in
		Group group = new Group(scrolledComposite, SWT.NONE);
		group.setLayout(new RowLayout(SWT.HORIZONTAL));
		scrolledComposite.setContent(group);
		scrolledComposite.setMinSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button x = (Button)e.getSource();
				System.out.println("Go back");
			}
		});
		
		// Temporarily create object types for the UI
		ArrayList<ObjectType> objectTypes = new ArrayList<ObjectType>();
		objectTypes.add(new ObjectType("Car", new ArrayList<Characteristic>()));
		objectTypes.add(new ObjectType("Cell Phone", new ArrayList<Characteristic>()));
		objectTypes.add(new ObjectType("Desktop", new ArrayList<Characteristic>()));
		objectTypes.add(new ObjectType("Laptop", new ArrayList<Characteristic>()));
		

		this.objectTypeButtons = new ArrayList<Button>();
		for(ObjectType o : objectTypes) {
			
			Group buttonGroup = new Group(group, SWT.NONE);			
			buttonGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			// Delete Button
			Button delbtn = new Button(buttonGroup, SWT.NONE);
			delbtn.setBounds(2, 16, 70, 21);
			delbtn.setText("D");
			delbtn.setData(o);
			delbtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					Button x = (Button)e.getSource();
					ObjectType clickedObjectType = (ObjectType)x.getData();
					System.out.println(clickedObjectType.getName());
				}
			});
			
			// Edit Button
			Button editbtn = new Button(buttonGroup, SWT.NONE);
			editbtn.setBounds(2, 16, 70, 21);
			editbtn.setText("E");
			editbtn.setData(o);
			editbtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					Button x = (Button)e.getSource();
					ObjectType clickedObjectType = (ObjectType)x.getData();
					System.out.println(clickedObjectType.getName());
				}
			});
						
			// Compare Button
			Button comparebtn = new Button(buttonGroup, SWT.NONE);
			comparebtn.setBounds(2, 16, 70, 21);
			comparebtn.setText(o.getName());
			comparebtn.setData(o);
			comparebtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					Button x = (Button)e.getSource();
					ObjectType clickedObjectType = (ObjectType)x.getData();
					System.out.println(clickedObjectType.getName());
				}
			});
		
		}
		
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
