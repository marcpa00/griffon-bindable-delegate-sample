package domain

import groovy.beans.Bindable

import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener


/**
 * Created with IntelliJ IDEA.
 * User: marcpa
 * Date: 2013-02-01
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
@Bindable
class Person {
    String name
    String lastName

    private propListener = { e ->
        println "Person : change of property $e.propertyName"
        println "         oldValue = '$e.oldValue'"
        println "         newValue = '$e.newValue'"
    }

    Person() {
        addPropertyChangeListener(propListener as PropertyChangeListener)
    }

    Person(Closure clos) {
        this()
        addPropertyChangeListener({ PropertyChangeEvent e ->
            clos.call(e)
        } as PropertyChangeListener)
    }
}


