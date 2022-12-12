import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';

class AuthTextAndButton extends StatelessWidget {
  final Function onTap;
  final String text1;
  final String text2;
  const AuthTextAndButton({
    Key? key,
    required this.onTap,
    required this.text1,
    required this.text2,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 24),
      child: RichText(
        text: TextSpan(
            style: const TextStyle(color: Colors.black54, fontSize: 16),
            children: [
              TextSpan(
                text: text1,
              ),
              const WidgetSpan(child: SizedBox(width: 5)),
              TextSpan(
                recognizer: TapGestureRecognizer()..onTap = () => onTap(),
                text: text2,
                style: const TextStyle(color: AppTheme.primary),
              )
            ]),
      ),
    );
  }
}
