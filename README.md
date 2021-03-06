QuickWin
========

A SuperCollider class for quickly creating GUIs to test out synths.

Released under GPL v.3.

What?
-----
* Create a quick win.
* Give it a synthdef to play with.
* Tell it about the controllable parameters.
* Done.

How?
----

    (
     var qw, params, labels, specs;
     
     qw = QuickWin("Demo");
     
     qw.synth = \default;

     params = [\freq,\amp,\pan];
     labels = ["Frequency", "Amp", "Pan"];
     specs = [ [220,880,440], [0,1,0.5], [-1,1,0] ];

     qw.addControls(params,labels,specs);
     qw.show;
    )

It relies on the Synth having a gated envelope, and doneAction 2 (so it frees itself once the gate is set to 0) - it creates a new Synth instance for every on/off button cycle, so if it's doneAction 0 you'll run into issues.

Why?
----
I got tired writing the same code over and over again to make GUIs..

Who?
----
This is mainly for myself, but released in the hope that someone else might find it useful..

TODO
----
* Add support for doneAction 0 synths - have selectable mode?
* Fix minor layout issue - spacing is a little off
