import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/utils/sizes.dart';

class SubmitButton extends StatelessWidget {
  final String title;
  final void Function()? onPressed;
  final bool isLoading;
  const SubmitButton(
      {super.key, required this.title, this.onPressed, this.isLoading = false});

  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return MaterialButton(
      onPressed: onPressed,
      highlightColor: AppTheme.primary,
      disabledColor: AppTheme.primary.withAlpha(150),
      highlightElevation: 1,
      elevation: 0,
      color: AppTheme.primary,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
      child: Container(
        width: double.infinity,
        alignment: Alignment.center,
        padding: const EdgeInsets.symmetric(vertical: 20),
        child: isLoading
            ? SizedBox(
                height: Sizes.scaleVertical * 2,
                width: Sizes.scaleVertical * 2,
                child: const CircularProgressIndicator(
                  color: Colors.white,
                ),
              )
            : Text(
                title,
                style: const TextStyle(color: Colors.white, fontSize: 18),
              ),
      ),
    );
  }
}
