# Vapu Lite
Vapu Lite is a free minecraft injection hacked client. (Not [VAPE](https://vape.gg/))

*GitHub Action is Vapu Development Mod. Use at your own risk.*

![Minecraft](https://img.shields.io/badge/game-Minecraft-brightgreen) [![build](https://github.com/VapuClient/VapuLite/actions/workflows/build.yml/badge.svg)](https://github.com/VapuClient/VapuLite/actions/workflows/build.yml)

Requirement: 
[Minecraft Forge](https://minecraftforge.net)


## License
This project is subject to the [GNU General Public License v3.0](LICENSE). This does only apply for source code located directly in this clean repository. During the development and compilation process, additional source code may be used to which we have obtained no rights. Such code is not covered by the GPL license.

For those who are unfamiliar with the license, here is a summary of its main points. This is by no means legal advise nor legally binding.

You are allowed to
- use
- share
- modify

this project entirely or partially for free and even commercially. However, please consider the following:

- **You must disclose the source code of your modified work and the source code you took from this project. This means you are not allowed to use code from this project (even partially) in a closed-source (or even obfuscated) application.**
- **Your modified application must also be licensed under the GPL**

Do the above and share your source code with everyone; just like we do.

## Example Module
~~~java
package gq.vapulite.Vapu.modules;

import gq.vapulite.Vapu.ModuleType;
import org.lwjgl.input.Keyboard;

public class ExampleModule extends Module {
    public ExampleModule() {
        super("ExampleModule", Keyboard.KEY_NONE, ModuleType.Config,"Example Module");
        //Chinese language
        Chinese="ExampleModule";
    }
    
    //here is your code

}
~~~

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=VapuClient/VapuLite&type=Date)](https://star-history.com/#VapuClient/VapuLite&Date)
