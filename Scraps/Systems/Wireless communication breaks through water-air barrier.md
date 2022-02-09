# Wireless communication breaks through water-air barrier

In a novel system developed by MIT researchers, underwater sonar signals cause vibrations that can be decoded by an airborne receiver.

Rob Matheson | MIT News Office
Publication Date:August 22, 2018

MIT researchers have taken a step toward solving a longstanding challenge with wireless communication: direct data transmission between underwater and airborne devices.

Today, underwater sensors cannot share data with those on land, as both use different wireless signals that only work in their respective mediums. Radio signals that travel through air die very rapidly in water. Acoustic signals, or sonar, sent by underwater devices mostly reflect off the surface without ever breaking through. This causes inefficiencies and other issues for a variety of applications, such as ocean exploration and submarine-to-plane communication.

In a paper being presented at this week’s SIGCOMM conference, MIT Media Lab researchers have designed a system that tackles this problem in a novel way. An underwater transmitter directs a sonar signal to the water’s surface, causing tiny vibrations that correspond to the 1s and 0s transmitted. Above the surface, a highly sensitive receiver reads these minute disturbances and decodes the sonar signal.

“Trying to cross the air-water boundary with wireless signals has been an obstacle. Our idea is to transform the obstacle itself into a medium through which to communicate,” says Fadel Adib, an assistant professor in the Media Lab, who is leading this research. He co-authored the paper with his graduate student Francesco Tonolini.

The system, called “translational acoustic-RF communication” (TARF), is still in its early stages, Adib says. But it represents a “milestone,” he says, that could open new capabilities in water-air communications. Using the system, military submarines, for instance, wouldn’t need to surface to communicate with airplanes, compromising their location. And underwater drones that monitor marine life wouldn’t need to constantly resurface from deep dives to send data to researchers.

Another promising application is aiding searches for planes that go missing underwater. “Acoustic transmitting beacons can be implemented in, say, a plane’s black box,” Adib says. “If it transmits a signal every once in a while, you’d be able to use the system to pick up that signal.”

## Decoding vibrations

Today’s technological workarounds to this wireless communication issue suffer from various drawbacks. Buoys, for instance, have been designed to pick up sonar waves, process the data, and shoot radio signals to airborne receivers. But these can drift away and get lost. Many are also required to cover large areas, making them impracticable for, say, submarine-to-surface communications.

TARF includes an underwater acoustic transmitter that sends sonar signals using a standard acoustic speaker. The signals travel as pressure waves of different frequencies corresponding to different data bits. For example, when the transmitter wants to send a 0, it can transmit a wave traveling at 100 hertz; for a 1, it can transmit a 200-hertz wave. When the signal hits the surface, it causes tiny ripples in the water, only a few micrometers in height, corresponding to those frequencies.

To achieve high data rates, the system transmits multiple frequencies at the same time, building on a modulation scheme used in wireless communication, called orthogonal frequency-division multiplexing. This lets the researchers transmit hundreds of bits at once.

Positioned in the air above the transmitter is a new type of extremely-high-frequency radar that processes signals in the millimeter wave spectrum of wireless transmission, between 30 and 300 gigahertz. (That’s the band where the upcoming high-frequency 5G wireless network will operate.)

The radar, which looks like a pair of cones, transmits a radio signal that reflects off the vibrating surface and rebounds back to the radar. Due to the way the signal collides with the surface vibrations, the signal returns with a slightly modulated angle that corresponds exactly to the data bit sent by the sonar signal. A vibration on the water surface representing a 0 bit, for instance, will cause the reflected signal’s angle to vibrate at 100 hertz.

“The radar reflection is going to vary a little bit whenever you have any form of displacement like on the surface of the water,” Adib says. “By picking up these tiny angle changes, we can pick up these variations that correspond to the sonar signal.”

## Listening to “the whisper”

A key challenge was helping the radar detect the water surface. To do so, the researchers employed a technology that detects reflections in an environment and organizes them by distance and power. As water has the most powerful reflection in the new system’s environment, the radar knows the distance to the surface. Once that’s established, it zooms in on the vibrations at that distance, ignoring all other nearby disturbances.

The next major challenge was capturing micrometer waves surrounded by much larger, natural waves. The smallest ocean ripples on calm days, called capillary waves, are only about 2 centimeters tall, but that’s 100,000 times larger than the vibrations. Rougher seas can create waves 1 million times larger. “This interferes with the tiny acoustic vibrations at the water surface,” Adib says. “It’s as if someone’s screaming and you’re trying to hear someone whispering at the same time.”

To solve this, the researchers developed sophisticated signal-processing algorithms. Natural waves occur at about 1 or 2 hertz — or, a wave or two moving over the signal area every second. The sonar vibrations of 100 to 200 hertz, however, are a hundred times faster. Because of this frequency differential, the algorithm zeroes in on the fast-moving waves while ignoring the slower ones.

## Testing the waters

The researchers took TARF through 500 test runs in a water tank and in two different swimming pools on MIT’s campus.

In the tank, the radar was placed at ranges from 20 centimeters to 40 centimeters above the surface, and the sonar transmitter was placed from 5 centimeters to 70 centimeters below the surface. In the pools, the radar was positioned about 30 centimeters above surface, while the transmitter was immersed about 3.5 meters below. In these experiments, the researchers also had swimmers creating waves that rose to about 16 centimeters.

In both settings, TARF was able to accurately decode various data — such as the sentence, “Hello! from underwater” — at hundreds of bits per second, similar to standard data rates for underwater communications. “Even while there were swimmers swimming around and causing disturbances and water currents, we were able to decode these signals quickly and accurately,” Adib says.

In waves higher than 16 centimeters, however, the system isn’t able to decode signals. The next steps are, among other things, refining the system to work in rougher waters. “It can deal with calm days and deal with certain water disturbances. But [to make it practical] we need this to work on all days and all weathers,” Adib says.

“TARF is the first system that demonstrates that it is feasible to receive underwater acoustic transmissions from the air using radar,” says Aaron Schulman, an assistant professor of computer science and engineering at the University of California at San Diego. “I expect this new radar-acoustic technology will benefit researchers in fields that depend on underwater acoustics (for example, marine biology), and will inspire the scientific community to investigate how to make radar-acoustic links practical and robust.”

The researchers also hope that their system could eventually enable an airborne drone or plane flying across a water’s surface to constantly pick up and decode the sonar signals as it zooms by.

The research was supported, in part, by the National Science Foundation.

https://news.mit.edu/2018/wireless-communication-through-water-air-0822
