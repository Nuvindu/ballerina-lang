public function test() {
    do {
        fail error("error!");
    } on fail var error(m) {
        io:println(m);
    }
}
