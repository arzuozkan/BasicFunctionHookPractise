# BasicFunctionHookPractise
This repo is a basic frida usage example for the people who starts to hook method with frida tool

# How To
Download the apk file or download the source code and build as an apk.

[app-debug.zip](https://github.com/arzuozkan/BasicFunctionHookPractise/files/8315239/app-debug.zip)

Open the apk file by using emulator. Genymotion and jadx tools are used in this basic walkthrough. 

# Walkthrough

![resim](https://user-images.githubusercontent.com/48025290/159259107-03ff78ae-f023-43d8-967c-4190a2573752.png)

When the application is opened, the screen looks like above the image. If we clicked the "CLCIK" button, you can see the Toast message. 
The button is clicked more than once,different toast messages will appear.

open the apk file with jadx tool. There is a function called `hookMe()`.  

![resim](https://user-images.githubusercontent.com/48025290/159261289-30b2b819-4aa5-4490-9947-ed36406d06c0.png)

This function always returns true. Although we find the flag, we will not be able to submit it unless `hookMe()` function returns false.

![resim](https://user-images.githubusercontent.com/48025290/159261946-bf6a7473-163e-4bb0-9774-a8788f25f4ff.png)

It is just a simple function to understand how to hook method by using frida tool.
Assuming te frida is installed in pc and device. So skipped the installation part, we can control the frida server is running, commands `frida-ps -U` in terminal. It gives the list of running processes.

![resim](https://user-images.githubusercontent.com/48025290/159262859-b6943387-b3c5-4c70-a6ba-dad0b873d353.png)

Now, changed the `hookMe()` method and inserting the script to the application with frida tool.

```python
import  frida
import sys

code=""" 
Java.perform(function(){
    var Activity =Java.use("com.useryoo.basicfunctionhookpractise.MainActivity");
    Activity.hookMe.implementation=function(){
            send("hookMe got called and returned false");
            return false;
    };

});
"""

session=frida.get_usb_device().attach("BasicFunctionHookPractise")
script=session.create_script(code)

def on_message(message,data):
    print(message)

script.on("message",on_message)
print("executing code")
script.load()
sys.stdin.read()

```
Then we need to insert the code. Run the command in the terminal. If the frida-server closed, it raises an error as "frida.ServerNotRunningError: unable to connect to remote frida-server: closed".

> python hookMethod.py

![resim](https://user-images.githubusercontent.com/48025290/159264804-525509a0-ed5d-4c8f-9e10-dfc0b0e5fe85.png)

The code is executing, when you clicked the button, you will see the submit button and input field is visible. 
![resim](https://user-images.githubusercontent.com/48025290/159265961-123df2dd-e5bf-4713-98be-aa714e4de092.png)

Also,you can follow the process in terminal

![resim](https://user-images.githubusercontent.com/48025290/159266142-595457c4-dd4b-4a7b-93d8-153cfe322775.png)

