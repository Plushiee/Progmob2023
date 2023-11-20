class Album {
  final int userId;
  final int id;
  final String title;

  Album({
    required this.userId,
    required this.id,
    required this.title,
  });

  factory Album.fromJson(Map<String, dynamic> json) {
    if (json.containsKey('userId')) {
      return Album(
        userId: json['userId'] as int,
        id: json['id'] as int,
        title: json['title'] as String,
      );
    } else if (json.containsKey('id') && json.containsKey('title')) {
      return Album(
        userId: 0,
        id: json['id'] as int,
        title: json['title'] as String,
      );
    } else {
      throw FormatException('Failed to load album.');
    }
  }
}
