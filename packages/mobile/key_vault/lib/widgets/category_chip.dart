import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/utils/sizes.dart';

class CategoryChip extends StatelessWidget {
  final bool selected;
  const CategoryChip({super.key, required this.selected});

  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal),
      child: ChoiceChip(
        selected: selected,
        disabledColor: const Color(0xffF6F8F9),
        selectedColor: AppTheme.primary.withOpacity(0.5),
        label: const Text("Categoría"),
      ),
    );
  }
}
