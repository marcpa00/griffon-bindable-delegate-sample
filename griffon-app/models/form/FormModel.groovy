package form

import domain.Person

import static griffon.util.GriffonNameUtils.isBlank

@Bindable
class FormModel {

    // name and firstName are delegated to Person; our listeners are registered on a callback from Person's PropertyChangeListener
    @Delegate Person person = new Person({ PropertyChangeEvent e ->
        println "FormModel callback called on $e.propertyName, from '$e.oldValue' to '$e.newValue'"
        println "Propagating to our change listeners"
        this.propertyChangeListeners.each {
            it.propertyChange(e)
        }
    })

	String address
	
	boolean submitEnabled
	boolean resetEnabled
	
	private enabler = { e ->

        println "enabler : change of property $e.propertyName"
        println "          oldValue = '$e.oldValue'"
        println "          newValue = '$e.newValue'"

		submitEnabled = !isBlank(name) && !isBlank(lastName) && !isBlank(address)
		resetEnabled = !isBlank(name) || !isBlank(lastName) || !isBlank(address) 
	}

	FormModel() {
		addPropertyChangeListener(enabler as PropertyChangeListener)
	}




}