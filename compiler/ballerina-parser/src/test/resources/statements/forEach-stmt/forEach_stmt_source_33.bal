public function test() {
    foreach var v in membersList {
        fail error("error!");
    } on fail error(m) {
        io:println(m);
    }
}
