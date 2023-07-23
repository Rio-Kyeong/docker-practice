mongo -- "pg" <<EOF
    var rootUser = 'admin';
    var rootPassword = '1234';
    var admin = db.getSiblingDB('admin');
    admin.auth(rootUser, rootPassword);
    var user = 'user';
    var passwd = '1234';
    db.createUser({user: user, pwd: passwd, roles: ["readWrite"]});
EOF
