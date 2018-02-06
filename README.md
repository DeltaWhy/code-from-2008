# Code-from 2008
Stuff I made as a high school sophomore. Uploaded as-is from an old backup in Dropbox, with binaries and duplicates galore. The good stuff is under "Non-class Projects".

## Battleship
I never finished this one. It was obviously a battleship game but there's a weird mix of GUI and console code. Oh well.

## Beep/Beep2
This was a project to annoy everyone by playing tunes on the PC speaker (remember those)? The class computers didn't have any speakers attached, so I had to get creative and use some JNI thing I found online and definitely didn't understand.

## GridWorld
Some sort of practice for the AP Computer Science A test. Also not working, there's a library (provided by the AP folks) missing that's probably lost to the sands of time. If someone finds it, send me a pull request I guess?

## HelloWorld
Hello World standard, GUI, and overengineered poorly understood Model-View-Controller versions. That last one was intended as a joke in the same spirit as [FizzBuzzEnterpriseEdition](https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition) (though looks like mine predates it by quite some time), because I was bored in CS class.

## Katana
An encryption program, an implementation of [CipherSaber](http://ciphersaber.gurus.org/) (whose website looks to be unchanged since then). It's based on RC4, or ARCFOUR if you want to be pedantic (the reverse engineered version of the proprietary RC4, presumably identical). Pretty cool at the time and actually works. This should go without saying, but don't use this for anything serious... I had not yet heard about side channel attacks in high school, and even if I had, *Don't Roll Your Own Crypto Primitives* is rules 1 and 2 of cryptography for good reasons.

## RedJ (many versions)
I really hated BlueJ, the educational IDE that was installed on the school computers, but they were locked down pretty hard so I couldn't access the command prompt to just run `javac` like a sane person. So I made my own mini-IDE that just let me compile and run stuff, launching jEdit to actually edit files (since we couldn't install programs or run .exe's from our documents folder, but could run .jar's, and since Notepad is a drag). I'm still impressed that I managed to figure out how to deal with standard input and output without being able to launch a command prompt window. I also had config files rather than hard-coded paths, although the setup process was a bit weird.

I don't know if I ever considered just asking the admins to install Notepad++. My CS teacher was sympathetic but didn't have much more privileges on the computers than we did, so I ended up putting a lot of effort into creative workarounds. This may have been one of the things that sparked my eventual interest in security (along with the crypto stuff above, and a healthy dose of anti-Microsoft hatred spawned from early exposure to Linux and RMS's philosophy).

## Stars
The Java port of my best-selling TI-83 game (by which I mean I passed it around to a bunch of friends via link cable). I had set out to write Snake on my calculator in TI-BASIC but couldn't figure out how to do it without proper arrays, and ended up making this instead. I think it's pretty fun. You can go through the springs if you press the right key at the right time, which was a bug in the original that I decided to elevate to a feature. The standard version was speed 3 (roughly equivalent to the fastest I could get it running on the TI-83) and wall mode 1. I've uploaded this one [before](https://github.com/DeltaWhy/stars-java).

I actually published the TI-83 version on [ticalc.org](http://www.ticalc.org/pub/83/basic/games/arcade/) if you have access to an emulator or a real one and a USB link cable. I developed it mostly during math class of 2008-2009 and continued tweaking it all that year and beyond. Programming on such a constrained system was a really interesting challenge, as was optimizing the size and speed in a really weird BASIC dialect. There's even a simple checksum to prevent someone from casually cheating their high score. I think I maintained the record score, mostly since the game ran faster on my friends' TI-83+es and TI-84+es than on my hand-me-down TI-83.

My freshman year in college I made an Android version of this game for a hackathon which actually won me a prize. I'm sure it's sitting around on a backup drive somewhere...

## Tetris/Tetris2
I think I started this after giving up on Battleship. "Tetris" looks like an older version, since "Tetris2" has scoring and is missing the random "Button!" that I assume was originally for testing something. The pieces are randomly selected since I didn't know about Tetris's piece selection algorithm at the time. The scoring is probably different from the official version too. But hey, it works!
