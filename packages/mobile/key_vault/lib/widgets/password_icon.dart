import 'package:flutter/material.dart';
import 'package:key_vault/models/password.dart';
import 'package:key_vault/utils/sizes.dart';

class PasswordIcon extends StatelessWidget {
  const PasswordIcon({
    Key? key,
    required this.password,
  }) : super(key: key);

  final Password password;

  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return CircleAvatar(
        backgroundColor: const Color(0xffF2F0E9),
        maxRadius: 35,
        child: password.icon == null
            ? Text(
                password.name[0].toUpperCase(),
                style: const TextStyle(
                    color: Color(0xffF0C028),
                    fontSize: 20,
                    fontWeight: FontWeight.bold),
              )
            : Icon(
                size: Sizes.scaleHorizontal * 10,
                Icons.reddit,
              ));
  }
}
