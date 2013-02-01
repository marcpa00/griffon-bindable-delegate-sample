package form

import groovy.beans.Bindable
import static griffon.util.GriffonNameUtils.isBlank
import java.beans.PropertyChangeListener

@Bindable
class FormModel {
	
	String name
	String lastName
	String address
	
	boolean submitEnabled
	boolean resetEnabled
	
	private enabler = { e ->
		submitEnabled = !isBlank(name) && !isBlank(lastName) && !isBlank(address)
		resetEnabled = !isBlank(name) || !isBlank(lastName) || !isBlank(address) 
	}
	
	FormModel() {
		addPropertyChangeListener(enabler as PropertyChangeListener)
	}
}