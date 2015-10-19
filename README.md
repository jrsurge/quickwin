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

Why?
----
I got tired writing the same code over and over again to make GUIs..

Who?
----
This is mainly for myself, but released in the hope that someone else might find it useful..
