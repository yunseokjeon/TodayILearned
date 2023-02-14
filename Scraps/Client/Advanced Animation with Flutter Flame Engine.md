[Advanced Animation with Flutter Flame Engine](https://medium.com/kbtg-life/advanced-animation-with-flutter-flame-engine-ep-1-sprite-sheet-24fb45e888cc)

```dart 
// main.dart
import 'package:flame/game.dart';
import 'package:flutter/material.dart';
import 'sprite_sheet.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Stack(children: [
      Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text(
                'You have pushed the button this many times:',
              ),
              Text(
                '$_counter',
                style: Theme.of(context).textTheme.headline4,
              ),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: _incrementCounter,
          tooltip: 'Increment',
          child: const Icon(Icons.add),
        ),
      ),
      SizedBox(
          width: 12,
          height: 12,
          child: Align(
              alignment: Alignment.topCenter,
              child: GameWidget(game: SpriteSheetWidget()))),
    ]);
  }
}
```

```dart 
// sprite_sheet.dart

import 'package:flame/game.dart';
import 'package:flame/input.dart';
import 'package:flame/sprite.dart';
import 'package:flame/components.dart';
import 'package:flame/effects.dart';
import 'package:flutter/animation.dart';

class SpriteSheetWidget extends FlameGame with TapDetector {
  @override
  void onTapDown(TapDownInfo info) {
    print(info.eventPosition.game);
  }

  @override
  Future<void> onLoad() async {
    final spriteSheet = SpriteSheet(
      // 애니메이션을 구성하는 여러 장의 셀이 하나의 이미지에 들어있다.
      image: await images.load('cat_sprite_long.png'),
      // srcSize를 통해 이미지 속의 셀을 자른다.
      srcSize: Vector2(50.0, 50.0),
    );
    final spriteSize = Vector2(50.0, 50.0);

    final animation =
        spriteSheet.createAnimation(row: 0, stepTime: 0.1, to: 60);

    final component1 = SpriteAnimationComponent(
      animation: animation,
      scale: Vector2(0.4, 0.4),
      position: Vector2(160, -5),
      size: spriteSize,
    );

    add(
      component1
        ..add(
          MoveEffect.to(
            Vector2(250, -5),
            EffectController(
              duration: 10,
              reverseDuration: 10,
              infinite: true,
              curve: Curves.linear,
            ),
          ),
        ),
    );
  }
}
```