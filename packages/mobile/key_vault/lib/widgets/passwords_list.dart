import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:key_vault/models/password.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';

class PasswordsList extends StatelessWidget {
  final bool isEmpty;
  final List<Password> passwords;

  final assetName = "assets/empty-state-illustration.svg";
  final String? headerTitle;
  const PasswordsList({
    Key? key,
    required this.isEmpty,
    required this.passwords,
    this.headerTitle,
  }) : super(key: key);
  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return isEmpty
        ? _EmptyPasswordList(assetName: assetName)
        : Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              headerTitle != null
                  ? const ListTitle(text: "Agregadas recientemente")
                  : const SizedBox(),
              ListView.separated(
                physics: const NeverScrollableScrollPhysics(),
                padding: EdgeInsets.symmetric(
                    horizontal: Sizes.scaleHorizontal * 5,
                    vertical: Sizes.scaleVertical * 1.5),
                shrinkWrap: true,
                separatorBuilder: (context, index) => SizedBox(
                  height: Sizes.scaleVertical,
                ),
                itemBuilder: (context, index) => PasswordListItem(
                  password: passwords[index],
                ),
                itemCount: passwords.length,
              )
            ],
          );
  }
}

class _EmptyPasswordList extends StatelessWidget {
  const _EmptyPasswordList({
    Key? key,
    required this.assetName,
  }) : super(key: key);

  final String assetName;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(top: Sizes.scaleVertical * 3),
      child: Column(children: [
        SvgPicture.asset(
          assetName,
          height: Sizes.scaleVertical * 30,
        ),
        Padding(
          padding: const EdgeInsets.all(8.0),
          child: Opacity(
            opacity: 0.3,
            child: Text(
              "Todavía no guardaste ninguna contraseña",
              style: Theme.of(context).textTheme.headline6,
              textAlign: TextAlign.center,
            ),
          ),
        ),
      ]),
    );
  }
}
