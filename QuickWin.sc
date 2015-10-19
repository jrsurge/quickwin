QuickWin{

	var prWinTitle, prWin, prLayout, prParams, <>synth, prSynth;

	*new{ | title="QuickWin" |
		^super.newCopyArgs(title).init;
	}

	init{
		prWin = Window(prWinTitle);
		prLayout = HLayout();
		synth = \default;
		prSynth = nil;
		prParams = List();

		prWin.onClose_({
			if(prSynth != nil,{prSynth.free;});
		});

	}

// This could probably be improved - there's lots of variable hiding going on
	addControls{ | params=#[\freq,\amp,\pan], labels=#["Frequency", "Amplitude", "Pan"], specs=#[ [220,880,440], [0,1,0.5], [-1,1,0] ] |

		prLayout.add( Button()
			.states_([
				["On",Color.white,Color.red],
				["Off",Color.black,Color.green]
			])
			.action_({ | v |
				if(v.value == 1,{
					prSynth = Synth(this.synth,prParams);
				},{
					prSynth.set(\gate,0);
					prSynth = nil;
				})
			})
		);

		params.do({ | param, i |
			var tmpLayout, tmpSpec;
			tmpLayout = VLayout();
			tmpSpec = ControlSpec(specs[i][0],specs[i][1],default:specs[i][2]);

			prParams.add(params[i]).add(specs[i][2]);

			tmpLayout.add(StaticText().string_(labels[i]).align_(\center));
			tmpLayout.add(Knob().mode_(\vert).action_({ | v |
				if(prSynth != nil,{
					prSynth.set(params[i],tmpSpec.map(v.value));
				});
				prParams[(i*2)+1] = tmpSpec.map(v.value);
			}).value_(tmpSpec.unmap(specs[i][2])));
			prLayout.add(tmpLayout);
		});
	}

	show{
		prWin.layout_(prLayout);
		prWin.front;
	}
}
