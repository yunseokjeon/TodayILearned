비결이 뭔지 물었다. 그는 “무조건 소리내서 반복해서 읽고 외웠다”는 무미건조한 답을 내놨다. ‘무소반읽외’ 학습법이다. 책 두 권을 정해놓고 완독할 때마다 바를 정(正)를 써가며 계속 외웠다. 어느새 바를 정 글자가 200개가 넘었다고 한다.
https://news.v.daum.net/v/20210823050053513

Let's say you're building an email client in C++. Your new application will run on a workstation used by several different people every day. But somewhere along the line you made a mistake. By sending themselves a specially crafted email, they can overflow your application’s memory buffer and send the application commands to retrieve email from other users’ inboxes.

This buffer overflow bug is a classic example of a memory safety issue, and it's one of the most common types of security problems in software. Around 70 percent of all security issues fixed between 2004 and 2018 in Microsoft products were memory safety issues, according to a presentation by Matt Miller of the Microsoft Security Response Centre, which triages all of the company's reported security issues.

Memory safety is a huge issue. Operating systems need to ensure that individual applications don’t leak data to each other. Web browsers need to ensure that a web app open in one tab can't grab data from another tab. Cloud computing platforms need to ensure that one user can't read data from another user.

“Memory safety isn’t just a technical issue, it’s a social issue as well,” Internet Security Research Group Executive Director Joshua Aas. “Memory safety issues don’t just produce buggy software. They can crash services that people rely on to make a living or find employment. Security flaws can rob us of our privacy. Software can and should be built in better ways.”

In October 2020, the Internet Security Research Group launched a program called Prossimo dedicated to promoting memory safety on the internet. So far their key initiatives are using the Rust programming language, which was open sourced by Mozilla in 2010 and makes it easier to write memory safe code.

C, C++, and other programming languages that require developers to write code to manage their software's memory are considered “memory unsafe” because even small bugs can create memory safety issues. "No matter how talented your developers are, they're going to make mistakes," Aas says. "The world's greatest, most competent systems programming teams routinely create memory safety issues. Just look at the security patch notes for practically any major project and you'll see example after example."

As Laura Thomson, VP of Engineering at the cloud edge computing company Fastly, puts it: "Writing C is like doing brain surgery unassisted."

Writing software in "memory safe" languages that automatically manage memory has long been shown to reduce this entire class of security vulnerability. Nonetheless, C and C++ code is still everywhere. Operating systems, networking software, web browsers, and hardware drivers have all traditionally been written in C or its object-oriented successor C++. Whether you're using Android, iOS, Windows, Linux, or a Mac, there's a lot of C/C++ under the hood despite the use of memory safe languages like C#, Java, and Swift in many applications.

There's a good reason for that. C and C++ are fast. They're reliable. Programmers already know how to use them, there are thousands of open source libraries written in them, and they have compilers for practically every thinkable chipset. 

Prossimo is focused on memory safety overall, not just Rust. But Rust may be more likely to disrupt the status quo in systems programming than any other language. 

"Rust is the first of a new set of languages introduced over the past 20 or so years that can really be used for things we've been stuck building in C or assembly code before," says Fastly CTO Tyler McMullen says. "It's memory performant, and has a compelling type system that lets you express higher-level concepts in a low-level language." Plus it interoperates well with other languages and can run on many platforms ranging from embedded systems to servers.

And Rust has traction. Rust was ranked the “most loved” programming language in Stack Overflow’s 2021 Developer Survey— the fifth year in a row the language took the top spot. Meanwhile, it’s become increasingly popular for everything from scientific computing to open source projects such as Deno, the new server-side JavaScript platform built by Node.js creator Ryan Dahl.

This year, Mozilla transferred stewardship of Rust to the Rust Foundation, a coalition founded by Amazon Web Services, Facebook, Google, Huawei, Microsoft, and Mozilla. It's a sign that the industry's biggest players are serious about the future of Rust.

Facebook, for example, now has more than 100 developers working with Rust, including some contributors to the core Rust programming language. Facebook isn’t abandoning other languages, but Rust is used for projects throughout the company, including the Diem blockchain, the Move programming language, and the next version of their Buck tool. “One of our primary goals being involved in the foundation is to work closely with the other excellent foundation members, and the Rust community, to help the Rust maintainers do their great work more efficiently in order to make Rust a mainstream language of choice for systems programming and beyond,” says Joel Marcey, Open Source Developer Advocate at Facebook and Member Director at the Rust Foundation.

Many companies are now using Rust to help secure their cloud computing platforms, including Amazon Web Services, Cloudflare, Fastly, and Microsoft Azure. 

"I wasn't sold on it a few years ago," McMullen says. But Fastly’s WebAssembly team asked to build some projects using Rust and McMullen was impressed with the performance and safety of the language. Now Rust is a core part of the company's stack. "Basically everything new we do on the backend is written in Rust," Thomson says. "The compute services our customers use to run code are all built on Rust."

McMullen says their goal is to make their entire data plane pipeline memory safe. "This is the sort of environment where we can't trust anyone," he says. "We can't trust the traffic coming in, we can't trust the code running on the servers. Writing in a language like Rust makes it easier to not have to trust."

AWS uses Rust in the networking stacks of multiple services, including EC2, S3, and CloudFront, says Shane Miller, the head of the Rust Platform team at Amazon Web Services. The cloud platform built much of its open source, container-oriented virtualization system Firecracker in Rust, as well as its Linux-based container operating system Bottlerocket. "Rust quickly became critical for delivering infrastructure at scale at AWS," says Miller, who is also chair of the Rust Foundation.

Doing greenfield development in memory-safe languages is a good start. But making the web more memory safe will require refactoring of older software as well. One of the most ambitious Rust programming language efforts is the push to add support for Rust in Linux, an undertaking not yet endorsed by Linus Torvalds, who told ZDnet that when it comes to Rust he's in the "wait and see camp."

In the meantime, the Rust for Linux team is working to make Rust and the kernel play nice together. For example, the Rust standard library assumes that memory allocations never return errors—a concept known as “infallible allocation.” When memory allocations fail, processes are terminated.  The catch is that this can cause kernel panics—in other words, the operating system halts entirely in response to what it considers a fatal error. That’s bad news if you’re running multiple workloads in parallel. The Rust for Linux team, sponsored by Google, recently worked around the issue using a custom Rust library, but project lead Miguel Ojeda wrote that he hopes to see the issue fixed upstream in Rust itself. "We have already started this process and some changes have been already accepted upstream," he wrote to the kernel mailing list.

If and when Rust for Linux is merged, it will be the first time the Linux kernel has officially supported a language other than C or assembly, says kernel developer Greg Kroah-Hartman.

Linux isn't the only operating system using Rust. In 2019, Adam Burch of Microsoft's Hyper-V engineering team announced in a blog post that he was rewriting a low-level system component of the Windows codebase in Rust, though he couldn't say what component. "Though the project is not yet finished, I can say that my experience with Rust has been generally positive. It’s a good choice for those looking to avoid common mistakes that often lead to security vulnerabilities in C++ code bases."

Rusting All The Things

If you’re ready to put Rust to use yourself, the Rust community offers a wide variety of resources for learning the language. But be warned. As much as Rust’s fans love the language, it does come with a catch: many programmers complain that Rust has a steep learning curve compared to other modern languages. "Engineers compare learning Rust to learning to eat your veggies," Miller says. "They love it once they understand it, but it can be daunting. So one thing I'm focusing on is turning the broccoli into a brownie. Making the learning experience awesome."

To address the issue, Amazon Web Services is hiring experts in computer science education to consult on the design of the language. Some of the improvements are straightforward, like providing additional code examples in the documentation. Other ideas the company hopes to contribute to Rust are more complex. For example, rethinking Rust’s compilers.

But even for Rust’s biggest fans it's still hard to imagine all of the world's memory unsafe code being rewritten in Rust. It takes a long time for old code to be retired, especially if it already works well. Thompson Reuters estimates that there are still around 220 billion lines of COBOL, an even older language that dates back to the 1950s, in production, powering about 43 percent of banking systems and 95 percent of ATM swipes. Fortran is likewise still common in business applications around the world.

That said, not all old C/C++ code needs to be rewritten.  "We're not trying to address thousands of projects," Aas says. "We're focused on the critical components that just about everyone uses. Things like web servers, kernels, TLS, DNS, NTP—the core fabric of the internet."

"In some cases you can replace software component by component," Aas explains. "You can replace a memory unsafe library for a memory safe one and, over time, make the entire application more memory safe.”

Prossimo's work with Curl is a great example of this. Curl is written mostly in C, but Prossimo sponsored efforts to add support for Rust-based TLS and HTTP networking libraries in Curl. That makes it possible to use Rust for the parts of the application that touch the edge of the network without having to completely rewrite Curl. Curl doesn't default to using the Rust-based libraries, but anyone who prefers them can turn them on. "Our hope is that as the memory safe modules are tested and improved that they'll eventually become the default in all the major Linux distributions," Aas says.

Meanwhile, there are ways to sandbox memory unsafe code to minimize the risks associated with it. For example, Fastly is running much of their C/C++-based software through their Rust-based WebAssembly run-time. "It's a neat little way of putting all that software into Rust," McMullen says.

Bugs, including security vulnerabilities, are of course inevitable. But it’s entirely possible to eliminate a large swath of memory safety issues by choosing a programming language that protects against them. Rust offers an increasingly popular language that is not only performant, powerful, and open source, but also makes the internet safer for everyone.

https://github.com/readme/featured/rust-programming
