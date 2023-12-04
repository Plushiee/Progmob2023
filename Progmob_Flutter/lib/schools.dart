import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class Schools extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(left: 20, top: 50, right: 20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    InkWell(
                      onTap: () {
                        Navigator.pop(context);
                      },
                      child: SvgPicture.asset("assets/icons/arrow-left.svg"),
                    ),
                    SvgPicture.asset("assets/icons/more-vertical.svg"),
                  ],
                ),
                SizedBox(height: 20),
              ],
            ),
          ),
          Expanded(
            child: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  // Add your cards here
                  Card(
                    color: Colors.white,
                    child: Column(
                      children: [
                        ListTile(
                          title: const Text("Nikolaus Pastika"),
                          leading: Icon(Icons.person),
                          subtitle: Text("nikolaus@gmail.com"),
                        ),
                        UserAccountsDrawerHeader(
                          currentAccountPicture: CircleAvatar(
                            backgroundColor: Colors.white,
                            child: Image.asset(
                              "assets/images/user.png",
                              width: 70,
                              height: 70,
                              fit: BoxFit.cover,
                            ),
                          ),
                          accountName: Text("Mahasiswa Prodi Sistem Informasi"),
                          accountEmail:
                          Text("Universitas Kristen Duta Wacana"),
                        ),
                      ],
                    ),
                  ),
                  Card(
                    color: Colors.white,
                    child: Column(
                      children: [
                        ListTile(
                          title: const Text("Jerry Marcelino"),
                          leading: Icon(Icons.person),
                          subtitle: Text("jerry@gmail.com"),
                        ),
                        UserAccountsDrawerHeader(
                          currentAccountPicture: CircleAvatar(
                            backgroundColor: Colors.white,
                            child: Image.asset(
                              "assets/images/user.png",
                              width: 70,
                              height: 70,
                              fit: BoxFit.cover,
                            ),
                          ),
                          accountName: Text("Mahasiswa Prodi Sistem Informasi"),
                          accountEmail:
                          Text("Universitas Kristen Duta Wacana"),
                        ),
                      ],
                    ),
                  ),
                  Card(
                    color: Colors.white,
                    child: Column(
                      children: [
                        ListTile(
                          title: const Text("Antonius Istori"),
                          leading: Icon(Icons.person),
                          subtitle: Text("antonius@gmail.com"),
                        ),
                        UserAccountsDrawerHeader(
                          currentAccountPicture: CircleAvatar(
                            backgroundColor: Colors.white,
                            child: Image.asset(
                              "assets/images/user.png",
                              width: 70,
                              height: 70,
                              fit: BoxFit.cover,
                            ),
                          ),
                          accountName: Text("Mahasiswa Prodi Sistem Informasi"),
                          accountEmail:
                          Text("Universitas Kristen Duta Wacana"),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}


