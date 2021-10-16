Let's say you're building an email client in C++. Your new application will run on a workstation used by several different people every day. But somewhere along the line you made a mistake. By sending themselves a specially crafted email, they can overflow your application’s memory buffer and send the application commands to retrieve email from other users’ inboxes.

This buffer overflow bug is a classic example of a memory safety issue, and it's one of the most common types of security problems in software. Around 70 percent of all security issues fixed between 2004 and 2018 in Microsoft products were memory safety issues, according to a presentation by Matt Miller of the Microsoft Security Response Centre, which triages all of the company's reported security issues.

Memory safety is a huge issue. Operating systems need to ensure that individual applications don’t leak data to each other. Web browsers need to ensure that a web app open in one tab can't grab data from another tab. Cloud computing platforms need to ensure that one user can't read data from another user.

“Memory safety isn’t just a technical issue, it’s a social issue as well,” Internet Security Research Group Executive Director Joshua Aas. “Memory safety issues don’t just produce buggy software. They can crash services that people rely on to make a living or find employment. Security flaws can rob us of our privacy. Software can and should be built in better ways.”

https://github.com/readme/featured/rust-programming
