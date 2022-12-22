import 'package:flutter/material.dart';

import 'package:key_vault/widgets/widgets.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.white,
        body: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: const [
              _Header(),
              SizedBox(
                height: 35,
              ),
              _PasswordList(),
            ],
          ),
        ));
  }
}

class _PasswordList extends StatelessWidget {
  const _PasswordList({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const ListTitle(text: "Recently Added"),
        ListView.separated(
          physics: const NeverScrollableScrollPhysics(),
          padding: const EdgeInsets.symmetric(horizontal: 25, vertical: 15),
          shrinkWrap: true,
          separatorBuilder: (context, index) => const SizedBox(
            height: 10,
          ),
          itemBuilder: (context, index) => const _PasswordItem(),
          itemCount: 10,
        )
      ],
    );
  }
}

class _PasswordItem extends StatelessWidget {
  const _PasswordItem({
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
      subtitle: const Text("Added 2 min ago"),
      leading: const CircleAvatar(
        backgroundColor: Color(0xffF2F0E9),
        maxRadius: 35,
        child: Icon(
          size: 35,
          Icons.reddit,
        ),
      ),
      tileColor: Colors.white,
      onTap: () {},
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
          padding: const EdgeInsets.only(right: 35, left: 35, bottom: 20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const _Head(),
              const SizedBox(
                height: 30,
              ),
              const _Title(),
              const SizedBox(
                height: 15,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [
                  CategoryHeaderCard(),
                  CategoryHeaderCard(),
                  CategoryHeaderCard(),
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
      children: const [
        Text(
          "Manage",
          style: TextStyle(fontSize: 18),
        ),
        SizedBox(
          height: 5,
        ),
        Text(
          "Your Password",
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
    return Container(
      padding: const EdgeInsets.only(top: 10),
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
      padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 20),
      child: Column(
        children: const [
          CircleAvatar(
            backgroundColor: Color(0xffFEF9EA),
            maxRadius: 28,
            child: Icon(
              Icons.work_outlined,
              color: Color(0xffF0C028),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          Text(
            "CodeaIT",
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
          ),
          SizedBox(
            height: 2,
          ),
          Text(
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
