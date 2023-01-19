import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:key_vault/models/models.dart' show Password;
import 'package:key_vault/services/services.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';
import 'package:timeago/timeago.dart' as timeago;

class PasswordListItem extends StatelessWidget {
  final Password password;

  const PasswordListItem({
    Key? key,
    required this.password,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListTile(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(20),
        ),
        title: Text(
          password.name,
          style: const TextStyle(fontWeight: FontWeight.bold),
        ),
        contentPadding: EdgeInsets.zero,
        subtitle: Text(
          timeago.format(DateTime.parse(password.createdAt),
              locale: 'es', allowFromNow: true),
        ),
        leading: _Leading(password: password),
        tileColor: Colors.white,
        onTap: () async {
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

class _Leading extends StatelessWidget {
  const _Leading({
    Key? key,
    required this.password,
  }) : super(key: key);

  final Password password;

  @override
  Widget build(BuildContext context) {
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
