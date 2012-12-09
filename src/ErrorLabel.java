
import java.awt.Color;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class ErrorLabel extends JLabel {
	
	private Error currentError = null;
	private static CopyOnWriteArrayList<Error> errorList = new CopyOnWriteArrayList<Error>();
	
	public ErrorLabel(String text) {
		super(text);
	}
	
	public void addError(Error error) {
		if (!errorList.contains(error)) {
			errorList.add(error);
			refresh();
		}
	}
	
	public void removeError(Error error) {
		if (errorList.contains(error)) {
			errorList.remove(error);
			refresh();
		}
	}
	
	public void setCurrentError(Error error) {
		currentError = error;
	}
	
	public CopyOnWriteArrayList<Error> getErrorList() {
		return errorList;
	}
	
	public void refresh() {
		
		if (errorList.isEmpty()) {
			currentError = null;
		} else {
			int highest = Integer.MIN_VALUE;
			for (Error e : errorList) {
				if (e.getErrorCode() > highest) {
					highest = e.getErrorCode();
					currentError = e;
				}
			}
		}
		
		if (currentError != null) {
			this.setForeground(currentError.getErrorColor());
			this.setText(currentError.getErrorText());
		} else {
			this.setText("");
		}
	}
	
	
	

}

class Error{
	private String errorText;
	private Color errorColor;
	private int errorCode;
	
	public Error(String errorText, Color errorColor, int errorCode) {
		this.errorText = errorText;
		this.errorColor = errorColor;
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorText() {
		return errorText;
	}
	
	public Color getErrorColor() {
		return errorColor;
	}
}
