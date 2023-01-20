import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:key_vault/models/models.dart' show Password;
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';
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
        leading: PasswordIcon(password: password),
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
                  child: BottomSheetModal(id: '${password.id}'));
            },
          );
        });
  }
}
