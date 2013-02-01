griffon-bindable-delegate-sample
================================

Sample griffon project where a domain class is used as a Delegate from a model while still being Bindable.

I've taken the form application from "Griffon In Action" book (section 3.1) and modified it to show a
proof of concept of combining @Bindable with @Delegate.

The approach is not as elegant as having the AST transformations somewhat combined, but it does work and
may help in designing an elegant solution.  The downside of my solution is the polution introduced in the
domain model class, i.e. you have to make it conciously collaborate with the griffon model class for this to work.

Rationale / history
-------------------

My current griffon project needs to be called from the command-line as well as a GUI program.  I managed to 
make this work, but it has the drawback of carrying the griffon and swing framework run-time initialization, 
including the requirement to be able to open a window on a display, which makes it less amenable to headless
execution.

Andres suggested to break the application into different parts where a common part would be used by the
command-line and gui parts.  I did exactly that and with the added support for gradle builds in griffon 1.2.0,
I even organized things into a multiproject setup (core, cli, gui) where only the gui is a griffon project.
The only thing that breaks is the bindings : naturally, I wanted to stay DRY so I did not want to have a
domain class almost identical as the griffon model class, so @Delegate seemed the way to go.  However, as
also found by Rick Jensen here:

http://griffon-user.3225736.n2.nabble.com/Bind-in-the-view-and-Bind-PropertyListener-in-the-model-don-t-work-cleanly-with-Delegate-td7270497.html

@Bindable and @Delegate are not magically combined.

So I took the ideas exposed by Rick in that thread and came with the idea of using a callback to "percolate" 
property change event from domain to griffom model and applied that on a small working example taken from the book.
