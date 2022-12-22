import 'package:flutter/widgets.dart';

class Sizes {
  static late MediaQueryData _mediaQueryData;
  static late double screenWidth;
  static late double screenHeight;
  static late double scaleHorizontal;
  static late double scaleVertical;

  Sizes(BuildContext context) {
    _mediaQueryData = MediaQuery.of(context);
    screenWidth = _mediaQueryData.size.width;
    screenHeight = _mediaQueryData.size.height;
    scaleHorizontal = screenWidth / 100;
    scaleVertical = screenHeight / 100;
  }
}
