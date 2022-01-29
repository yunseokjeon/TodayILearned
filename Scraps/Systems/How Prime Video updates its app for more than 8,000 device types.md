Division of labor

To enable efficient updates on a wide variety of devices while still maintaining performance, the Prime Video app has two parts: a high-performance engine written in C++ that is stored on-device and an easy-to-update component that is downloaded every time the app launches.

In the figure above, the stuff on device is a thin C++ layer that includes a JavaScript virtual machine (VM) and the components required to run the Prime Video application, which handle input, the media pipeline, and such processes as such as network access, image decoding, and window events handling.

The stuff we download (at run time) includes the application code, along with low-level components that handle scene management, the animation system, graphics rendering, layout, and resource management, among other things. Historically, these components all used JavaScript

This architecture split allows us to deliver new features and bug fixes without having to go through the very slow process of updating the C++ layer. The downloadable code is delivered through a fully automated continuous integration and delivery pipeline that can release updates as often as every few hours. However, we have a constant tension between writing code that’s performant (C++) and writing less-performant code that we can update with ease (JavaScript).


WebAssembly

Wasm provides a compilation target for programming languages that offer more expressivity than JavaScript does, such as C or Rust. Like JavaScript code, compiled Wasm binaries run on a VM that provides a uniform interface between code and hardware, regardless of device.

Wasm was initially intended for web browsers, but there are now standalone applications of Wasm VMs outside the browser, such as running Internet-of-things software, game mods, and server-side workloads.

https://www.amazon.science/blog/how-prime-video-updates-its-app-for-more-than-8-000-device-types

