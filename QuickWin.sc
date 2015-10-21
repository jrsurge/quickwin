QuickWin{

	var prWinTitle, prWin, prLayout, prParams, <>synth, prSynth;

	*new{ | title="QuickWin" |
		^super.newCopyArgs(title).init;
	}

	init{
		prWin = Window(prWinTitle);
		prLayout = GridLayout();
		prLayout.spacing_(10);
		prLayout.margins_(10);
		prLayout.setColumnStretch(1,2);
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
		,0,0);

		params.do({ | param, i |
			var tmpSpec, tmpSlider, tmpNumBox;
			tmpSpec = ControlSpec(specs[i][0],specs[i][1],default:specs[i][2]);

			prParams.add(param).add(specs[i][2]);

			prLayout.add(StaticText().string_(labels[i]).align_(\center),i+1,0);

			tmpSlider = Slider()
			.minSize_(Size(100,20))
			.orientation_(\horizontal)
			.action_({ | v |
				if(prSynth != nil,{
					prSynth.set(param,tmpSpec.map(v.value));
				});
				tmpNumBox.value_(tmpSpec.map(v.value));
				prParams[(i*2)+1] = tmpSpec.map(v.value);
			})
			.value_(tmpSpec.unmap(specs[i][2]));

			tmpNumBox = NumberBox()
			.action_({ | v |
				if(prSynth != nil,{
					prSynth.set(param,v.value);
				});
				tmpSlider.value_(tmpSpec.unmap(v.value));
				prParams[(i*2)+1] = v.value;
			})
			.value_(specs[i][2]);

			prLayout.add(tmpSlider,i+1,1);
			prLayout.add(tmpNumBox,i+1,2);
		});
	}

	show{
		prWin.layout_(prLayout);
		prWin.front;
	}
}
