MAKING YOUR FIRST FPS in Unity with FPS Microgame!

https://youtu.be/jE3ZJ_tCGTo

- Getting started creating video games can be really scary

and it can be hard to know where to begin.

To help solve this problem,

Unity has created Micro Games.

These are small but functional game projects

that are designed to help you get started

using Unity in an interactive way.

So far there are Micro Games

for Carting, Platformer, and FPS.

In this video, we'll of course dive into the FPS Micro Game,

how to download it, use it and start modifying it

to create your own unique game.

Currently Micro Games run

on the most stable version of Unity, which is 2018.4.

So we'll be using that in this video.

Support for 2019.x is coming.

So stay tuned for that.

And with that, let's dive right into it.

What's that?

- Shotgun mic.

(gunshot)

- So the first thing that we want to do

is open up the Unity Hub.

And over here on the left,

we can navigate to the Learn panel.

As you can see, there are different projects available here,

and one of them is the FPS Micro Game.

And if we click this, you can see that we can download

and open a project based on this FPS Micro Game.

And there's also a link here

to some written online tutorials on using it.

We'll have a look at these later,

but for now, let's just go ahead and open up the project.

There we go.

Remember, this is also available

through the Unity Asset Store.

We'll of course have a link

to everything in the description.

So as you can see, we are met by this Welcome Screen here.

And if we just hit the Get Started button,

we can see that on the right here,

we have a window called Tutorials.

These are some interactive tutorials

that will help you get started with Unity

and figuring out this project.

If for some reason you don't have this panel,

remember you can always find it

under Tutorials, Show Tutorials.

And there we go.

Now, of course, I've tried this out before.

So all of these are marked as completed,

but you can actually track your progress here.

And if we just go into the first one called Playtest,

this is now going to guide us through

how we can play test our games.

Let's start this tutorial.

As you can see,

we need to click on the play button here

and this way we'll enter Play mode.

And we can actually move around our character.

You can see character movement is here.

We have some shooting where we can kill enemies.

There are pickups as well.

And if we go down here,

there's a kind of a boss enemy that we can fight against.

And we can even scope in with a weapon.

We can pick this up to gain health.

So there are a bunch of systems already working here.

There is movement, health, weapons with cool-down.

There are enemies boss fights, objectives.

And as you can see, there's even a menu screen

where we can go in and view controls and replay the game.

You will also notice

that if you just go past our enemy here,

there's actually an entire empty space here

where we can place rooms.

So it's really easy to start adding on to this level.

Now I'm going to press the Tab key here to pause the game,

and then I'm going to go up here and stop playing.

And if we focus on the hierarchy here,

we can select the player and inside of the inspector,

we can now adjust the gravity down force of our player

to whatever we like.

So if we would like our player

to be able to jump much higher,

we can simply set this to 10,

let's say Next and replay the game.

And you can see now our jump is much, much higher

because our gravity has been reduced.

So it's really, really easy to go in here

and add changes.

And that marks the first interactive tutorial.

And as you can see, there are more here

on first building a level, editing colours

and adding an enemy bot.

I'm going to show you how to do these things now.

So you don't have to go through these.

But if this is your first time ever using Unity,

I recommend doing these before continuing with video,

even though there might be a tiny bit of overlap.

So I'm going to close this window now.

And let's start by adding another room to our level.

Let's add one over here.

To do that, we go inside the Prefabs folder.

Here there's one called Level Rooms.

And here we have four rooms to choose from.

I'm going to choose the room small T Prefab

and simply click and drag to place it into our scene.

Let's hit F to focus on it.

And you'll notice if I click and drag on this,

it doesn't snap, so it's very hard to align this perfectly

with other rooms.

So to do that, we used Pro Grids.

As you can see, I already have mine right here.

If yours isn't showing up,

you can always go Tools, Pro Grids

and open up the Pro Grids window.

And the first thing that we want to do

is make sure to enable snapping right here.

We can also show and hide the grid using this button here,

and we can change the snap value up here

from three to something like 0.5.

So it's going to snap every 0.5 units.

Now, if we click and drag,

you can see that it's actually snapping into place.

Now I want to go ahead and rotate this entire room.

So I'm going to select our Rotation tool and click and drag

while holding down Control in order to snap in increments.

And I'm then simply going to move this over and align it

with our current doorframe.

Something like, that looks good.

And we've now added a room to our level.

We're going to of course drag in stuff from other folders

in order to populate this room.

So let's go under Prefabs level

and let's take Under Walls.

And here we have a Prefab called Wall Window,

which we can simply click and drag

in order to place into a scene.

Again, we can rotate this around

and put it wherever we like.

I'm going to hit Control + D to duplicate

and put one over here as well.

Now it's a good idea while doing this to stay organised.

So inside of a hierarchy here, let's select our Wall Window,

that's like the second one we just created.

I'm holding down Control to select multiple objects at once.

And let's also select our room.

Let's take all of these

and drag them under our level category.

There we go.

So now we can easily find them.

And whenever we're done making modifications to a level,

we want to make sure that we tell our enemies

how they can move through it.

We do this using a navigation mesh.

So if we go to the left here,

we see the NAV Mesh Surface Object.

As you can see this shows where our enemies can move,

and it definitely tends to avoid walls as well as obstacles.

However, our new room and new obstacles

don't seem to be included in this Navigation Mesh.

So all we need to do is really just to rebake it.

And we do that by hitting the Bake button.

And right away, you can see that it calculates

paths that go around our obstacles, awesome.

So with that, we are ready to start adding in more enemies.

Let's again go under Prefabs.

This time let's go undo the Enemies folder

and let's just take an enemy hovered bot.

Let's place it in the level here.

I'm going to put this one behind the wall window,

and I'm going duplicate it and put one over here as well.

Again, it's a good idea to stay organised.

So I'm going to take both of these

and put them under the Enemies category.

And now one of the really cool things

is that we can, of course, drag in

as many enemies that we'd like and place them all around,

but we can also edit individual settings for these enemies

inside of the inspector.

As you can see, there are a bunch of different things

that we can change about our enemies here.

For example, if I select the second hovered bot here,

we can see that this outer radius

that marks the detection range is currently overlapping

with some of the other rooms,

which is not something I would like.

I think it would be better if our enemies

don't detect our player before we're inside the room.

So let's simply decrease the detection range here

by clicking and dragging until it's within that room,

that looks much better.

And if we now hit Play,

we can of course move into the first room here

and kill that enemy.

And if we move into the second one,

we can see as soon as we step into that room,

the enemies will start firing on us.

And we've now created a cool new part to our level.

Awesome, so that should give you an idea

of how we can quickly add gameplay to your game.

But what if you want to customise the look of it?

Well, currently I think a lot of this

looks like a boring shade of grey.

I would like to go for a dark hell-like look.

So to change the feel of our level, let's go under FPS.

Let's go inside of the Art folder

and here's a separate folder for the materials.

Now I want to change some of the materials

for the level itself.

And in here, we can see that we indeed have

a bunch of different materials

controlling the different colours of our level.

If for example we want to change the colour of the walls,

well those are split into two separate materials,

one for each shade of the checker pattern.

So if we take for example, the wall dark grey here,

and turn that into some kind of dark red,

you can see that really changes the aesthetic of our level.

Let's actually copy this colour here,

take the other material and paste the colour in here,

and then just offset it a tiny bit

in order to keep that chequered look, really cool.

We're going to of course also change the floors.

So for that, we need to go and do the floor dark grey here.

Let's make this a lot darker

and let's do the same thing with the floor grey.

There we go, with just a few changes,

we've created a much darker looking level

that I personally think looks a lot cooler.

And you can of course add your own materials as well.

If for example, you wanted apply your own colours

to these windows here,

well all we would do is simply right click,

go Create Material.

And we would call this the Window underscore Walls.

Then we can simply click and drag this onto our window walls

in order to apply that material.

And we can set the colour to anything that we'd like.

I'm just going to put these, add some kind of bright blue,

really, really cool.

So next up we can start adding some more gameplay elements.

And I think one of the most exciting things

to play around with is always new weapons.

So let's go ahead and make

some kind of cool launch or weapon.

To do that, let's go under FPS.

Let's go into the Prefabs folder and open up Pickups.

In here we have pickups for a jet pack,

a launcher weapon and a shotgun.

I'm just going to choose the launcher here.

And if we have a look in the Inspector here,

there are of course a bunch of things that we can change.

The main thing is that we can choose

what weapon is going to be picked up

whenever we run into this pickup.

And if we simply click on this,

it's actually going to take us to another Prefab

that is that weapon.

So here we have the weapon launcher.

And we could go in and modify this directly,

but instead I'm going to create a duplicate of this

so we can create our own version of the launcher.

So that said, Control + D in order to duplicate it.

Let's rename this to Weapon unnderscore Ultimate Launcher.

We can put that weapon named here as well.

So let's put in Ultimate Launcher

and of course you can go in here and create your own icon

and all that stuff.

But the main thing that I would like to change

is make this launcher shoot multiple bullets at once.

This is an ultimate launcher after all.

So let's go in here

and change the bullets per shot to three,

and let's spread them out with a bit of an angles.

So let's put in an angle here of 10

and that should now shoot out

a scatter of exploding bullets.

Of course, if you want to go in

and modify the bullets themselves, you can do that as well

by simply clicking this part in here

and you go right into the projectile you're firing.

So as you can see it's really easy to change the bullets

and weapons to anything that you'd like.

Now I'm going to go back into Prefabs here,

go on to Pickups and let's take the pickup launcher

and drag it into our scene.

I'm going to place mine somewhere over here.

So it's kind of a reward for killing the two enemies

in this room.

And here we need to make sure to replace

the default weapon launcher

with the one that we just created.

So that's going to Prefabs, Weapons

and we have the Weapon Ultimate Launcher right here.

So let's select our Pickup Launcher

and drag that right in instead.

So now we've referenced our Ultimate Launcher instead.

So that's the weapon we're going to be picking up.

And if we hit Play now,

we can see that we can run over

to this part of the level here.

And there's now a weapon we can pick up.

It says "Picked up weapon, Ultimate Launcher."

And if I press Two now, we can switch to this weapon.

And it's going to fire three individual bullets

with a spread angle of 10 like we told it to.

Really, really cool.

And it's now a really powerful weapon.

So we go to our boss here, I'm sure that we'll be able

to make quick progress of him, awesome.

Now one of the things that we can really do

to shape our game is change the objectives.

Right now, when we spawn, we just have an objective

to kill all the enemies.

And a lot of levels will be based on objectives like this.

However, we can definitely go in and change this.

So if we go to our Hierarchy here

where we also need to change our Pickup Launcher

to be under the Pickups category,

well then under general, there's an object

called Objective Kill Enemies.

So this is the current objective in our level.

As you can see, it has a description

that says "Defeat all the enemies in the map."

It's currently not said to be optional.

This is the main objective of the level.

The objective here is to kill all enemies

and it's going to display a message that says,

"Eliminate all the enemies."

Well, we could simply go in and change this.

So maybe we only want the player to kill two enemies

and have the rest of them be optional.

So we could simply disable this

and change the Kills to Complete objective to two,

and then change the display message

"To kill two enemies to win."

That's really easy.

In fact, let's go ahead and add

another objective on top of that.

Let's add an Objective Reach Point.

So if we go under Prefabs, you can see

there's an object here called Objective Reach Point,

and we can just drag this into our game

like any other object.

Let's place it out here on the bridge like thing,

somewhere around there.

And I want this to be an optional objective.

So we make sure that is optional is checked.

We'll give it a title and a description.

We could also add a component for displaying a message

if we wanted to do that as well.

But I think this is fine.

So if we just go ahead and hit Play now.

First of all, it says "Kill two enemies to win."

Then after four seconds,

it's going to display the "Eliminate two enemies."

And then it's going to tell us to reach an area.

So if we go over here, we can reach this area.

Boom, that completes the objective, Reach the Area.

We can also go over here and kill two enemies.

So this one maybe, and this one, and there we go,

all objectives have now been completed

and it's going to close down the level, really cool.

I'm just going to go to the objective Kill Enemies

and change that back

so that we need to kill all enemies.

And with that, we've kind of created

the very base of our game.

However, there's still a bunch of stuff

that you can choose to add or change about your game.

And this is the really cool thing about Micro Games

is that if we go to Tutorials and hit Show Tutorials

on the right here, there's a link to Martin tutorials.

This is again going to take us to this webpage here.

And that's because this is a collection of small tutorials

that show you how to change or add things to your game.

Some of it we've already done

like the one on game objectives,

but there's lots of other stuff like creating new weapons,

expanding on your levels, changing the enemies,

naming your game, changing the Sky Box and so on.

For example, there's this one here on adding new loot items

from destroying enemies.

And as you can see, it shows you how to do everything

step by step.

Even how you can create your own loot items.

Let's just quickly make one of our enemies

drop a jet pack, for example.

Well, we simply go into Unity,

we find the enemy that we want to do this.

Let's just make it the first one right here.

And this enemy is going to have a setting here

called Delude Prefab.

It's currently set to loot health,

but we can simply click on the tiny button here.

And that allows us to search through all the stuff

that we have in our project.

I'm just going to search for loot.

And as you can see,

it shows both a loot health and a loot jetpack.

So let's select the jetpack instead.

So let's just hit Play.

And if we now kill this enemy, a jetpack drops,

and if we pick that up and jump and press Space again,

we can start using this jetpack to fly around our level.

Awesome, of course, new mods are being added all the time,

but they're really just ideas on what you can do.

Feel free to go crazy with stuff that isn't on the list.

For example, the lighting is currently a bit dull,

so we could easily modify it.

If you go Window Rendering, Lighting Settings,

we can change the environment lighting here

to use the sky box

and we can set the intensity to something like 0.4

to make everything darker.

We then go and select our directional light in the scene

and play around with the intensity of this.

I'm just going to set it to 0.6 as well as the colour.

And we can of course add our own lights.

So if we right click here,

we can go Light, point light in order to create point lights

in our scene,

I'm just going to disable Snapping and the Grid View here.

I'm going to set the range to something much larger like 40

and the intensity to something between one and two.

And we can now start illuminating our scene.

(gentle music)

And right away, everything looks much more interesting.

Now once you've done all this work

and created something cool,

the unique thing about Unity

is that you can quickly put it on the web

and share it with your friends, which is really exciting.

You can do this

using the Build and Share Interactive Tutorial.

This will show you how to build your game

and upload it to the web.

Because of that, we need to make sure

that we have the web GL module installed

so that we can build for the web successfully.

We can check that by going to File, Build settings.

And if we here navigate to the web GL platform,

we can see that I don't have a web GL module loaded.

So we either download that by opening the Download page

or I like to do stuff like this through the Unity Hub.

So I'm just going to close down this project.

I'm going to hit Save, and I want to keep this project.

I'm going to put it in here

and I'm just going to call it My FPS.

Let's say Save.

Then inside the Unity Hub we can go in stores

and I'm going to find the version of Unity

that I'm using to run this project.

And that's 2018.4.

So I can go Add Modules

and simply select all the platforms

that I'd like to support.

I just want to go ahead and add the Web GL build.

That said done, Unity is going to instal Web GL.

And once it's done, we can go to projects

and here we can then add the project that we just saved.

So I called it My FPS, let's select that folder

and simply click it to open it up.

And if now go File,

Build Settings and choose the Web GL platform,

we can see that the module is installed.

And you're now ready to build your game.

Simply follow the steps in the Build and Share tutorial.

I'll do that now.

And as you can see, you get a webpage

where you can play the game.

And sharing it with your friends

is as easy as sending them a link.

And the cool thing is that if you make changes to your game

and follow the same build steps,

this website will update to feature all your changes.

Pretty cool, and that's pretty much it for this video.

If you enjoyed it, make sure it's a subscribe

and ring that notification bell

so you don't miss the next one.

And from here, it's up to you to have fun with your game

and come up with ideas on how to improve it.

I definitely encourage checking out some of the other mods

and just playing around with settings to see what they do.

Also, if you want to make your level look cooler

with some handy pre-made assets,

definitely check out our video on Snaps,

or if you want to get into creating levels

entirely from scratch, make sure to check out

our video on Pro Builder.

On that, thanks for watching

and I will see you in the next video.

Thanks for the awesome Patreon supporters

who donated in October

and a special thanks to Infinity PPR,

Lost to Violence, Love Forever,

Runin, Chris, Jacob Sanford, Face Amerify.

Peter Slenderman, Lilo Seth, Dennis Sullivan,

Alison the Fierce, Steve Customs, Cruise Swiveski,

Gregory Puce, Gnocchi Wasaki, The Mighty Zeus,

Daniel Sesanic and Erastus.

You guys rock.
