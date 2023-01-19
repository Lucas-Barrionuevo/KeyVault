import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:key_vault/services/services.dart';

import 'package:key_vault/utils/sizes.dart';

import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final passwords = Provider.of<PasswordService>(context);
    final isEmpty = passwords.passwords.isEmpty;
    final isLoading = passwords.isLoading;

    Sizes(context);
    return Scaffold(
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const _Header(),
              SizedBox(
                height: Sizes.scaleVertical * 4,
              ),
              isLoading ? const Loading() : _PasswordList(isEmpty: isEmpty),
            ],
          ),
        ));
  }
}

class _PasswordList extends StatelessWidget {
  final bool isEmpty;
  final assetName = "assets/empty-state-illustration.svg";
  const _PasswordList({
    Key? key,
    required this.isEmpty,
  }) : super(key: key);
  @override
  Widget build(BuildContext context) {
    final passwords = Provider.of<PasswordService>(context);
    return isEmpty
        ? _EmptyPasswordList(assetName: assetName)
        : Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const ListTitle(text: "Agregadas recientemente"),
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
                  password: passwords.passwords[index],
                ),
                itemCount: passwords.passwords.length,
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

class _Header extends StatelessWidget {
  const _Header({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return HeaderContainer(
      child: SafeArea(
        child: Padding(
          padding: EdgeInsets.only(
              right: Sizes.scaleHorizontal * 8,
              left: Sizes.scaleHorizontal * 8,
              bottom: Sizes.scaleVertical * 3),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const _Head(),
              SizedBox(
                height: Sizes.scaleVertical * 2,
              ),
              const _Title(),
              SizedBox(
                height: Sizes.scaleVertical * 3 / 2,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [
                  // CategoryHeaderCard(),
                  // CategoryHeaderCard(),
                  // CategoryHeaderCard(),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _Title extends StatelessWidget {
  const _Title({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const Text(
          "Administra",
          style: TextStyle(fontSize: 18),
        ),
        SizedBox(
          height: Sizes.scaleVertical / 2,
        ),
        const Text(
          "Tus contraseñas",
          style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
        ),
      ],
    );
  }
}

class _Head extends StatelessWidget {
  const _Head({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(top: Sizes.scaleVertical * 2),
      child: const CircleAvatar(
        child: Icon(Icons.access_alarm),
      ),
    );
  }
}

class CategoryHeaderCard extends StatelessWidget {
  const CategoryHeaderCard({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          color: Colors.white,
          boxShadow: const [
            BoxShadow(color: Color.fromRGBO(0, 0, 0, 0.07), blurRadius: 20)
          ],
          borderRadius: BorderRadius.circular(30)),
      padding: EdgeInsets.symmetric(
          horizontal: Sizes.scaleHorizontal * 3.5,
          vertical: Sizes.scaleVertical * 2.3),
      child: Column(
        children: [
          const CircleAvatar(
            backgroundColor: Color(0xffFEF9EA),
            maxRadius: 28,
            child: Icon(
              Icons.work_outlined,
              color: Color(0xffF0C028),
            ),
          ),
          SizedBox(
            height: Sizes.scaleVertical * 1.2,
          ),
          const Text(
            "Trabajo",
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
          ),
          const SizedBox(
            height: 2,
          ),
          const Text(
            "34",
            style: TextStyle(fontSize: 16, color: Colors.black26),
          ),
        ],
      ),
    );
  }
}

class HeaderContainer extends StatelessWidget {
  // ignore: prefer_typing_uninitialized_variables
  final child;
  const HeaderContainer({Key? key, this.child}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(40),
          boxShadow: const [
            BoxShadow(blurRadius: 10, color: Color(0xffEFE1BE))
          ],
          color: const Color(0xffFEF9EA)),
      child: child,
    );
  }
}
