import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/utils/sizes.dart';

class AuthLogo extends StatelessWidget {
  const AuthLogo({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Sizes(context);
    const assetName = "assets/logo.svg";
    return Center(
      child: Padding(
        padding: EdgeInsets.only(
            top: Sizes.scaleVertical * 9, bottom: Sizes.scaleHorizontal * 3),
        child: CircleAvatar(
          backgroundColor: AppTheme.primary,
          maxRadius: Sizes.scaleVertical * 4,
          child: Padding(
            padding: EdgeInsets.all(Sizes.scaleVertical * 1.5),
            child: SvgPicture.asset(
              assetName,
              color: Colors.white,
            ),
          ),
        ),
      ),
    );
  }
}
