/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

/**
 * @author andrewvojak
 *
 */
public class SampleHandlerTest {

	@Test
	public void test() throws SQLException, ClassNotFoundException {
		// System.out.println("> Creating server...");
		// final Server server = Server.createWebServer();
		//
		// System.out.println("> Starting server...");
		// server.start();
		//
		// try {
		// System.out.println("> " + server.getStatus());
		// Thread.sleep(60000);
		// } catch (final InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// System.out.println("> Stopping server...");
		// server.stop();
		// System.out.println("> Stopped.");
	}

	private static boolean wait = false;

	private static Cursor cursor = null;

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Change cursor");

		button.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void handleEvent(final Event arg0) {
				wait = !wait;

				if (cursor != null) {
					cursor.dispose();
				}

				cursor = wait ? new Cursor(display, SWT.CURSOR_WAIT) : new Cursor(display, SWT.CURSOR_ARROW);

				shell.setCursor(cursor);
			}
		});

		shell.setSize(200, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

		if (cursor != null) {
			cursor.dispose();
		}
	}

}
