import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';

class PasswordListItem extends StatelessWidget {
  const PasswordListItem({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListTile(
        title: const Text(
          "Probando",
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        contentPadding: EdgeInsets.zero,
        subtitle: const Text("Agregada hace 2 min."),
        leading: CircleAvatar(
          backgroundColor: const Color(0xffF2F0E9),
          maxRadius: 35,
          child: Icon(
            size: Sizes.scaleHorizontal * 10,
            Icons.reddit,
          ),
        ),
        tileColor: Colors.white,
        onTap: () {
          showModalBottomSheet<void>(
            context: context,
            barrierColor: Colors.transparent,
            shape: const RoundedRectangleBorder(
                borderRadius: BorderRadius.vertical(top: Radius.circular(20))),
            builder: (BuildContext context) {
              return BackdropFilter(
                  filter: ImageFilter.blur(sigmaX: 5, sigmaY: 5),
                  child: const BottomSheetModal());
            },
          );
        });
  }
}
