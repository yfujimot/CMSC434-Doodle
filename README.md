# CMSC434-Doodle by Yoshi Fujimoto (UMD Fall 2016)

##Features
1. Clear canvas
2. Adjust brush color (HSL - Hue, Saturation, Lightness colorspace)
3. Adjust opacity

## Custom Features
1. Save drawing as bitmap to local device gallery
2. Real-time color preview with opacity support

## Thought-process

Originally made the color palette and brush settings in a separate activity invoked by a "Settings" button on the main view.
After some interactions and playing with it myself, I realized the inefficiency, and the importance of not only seeing a
color preview, but actually drawing with it. I rearranged the main view layout and moved over the brush settings, so all
the tools, the drawing, and actions are all available on a single screen.

I used a mix of horizontal and vertical linear layout for the settings view, but ended up just combining it into a relative 
layout at the end.

## Usage

1. Git clone repo
2. Import project into Android Studio
3. Run application on android device or emulator

