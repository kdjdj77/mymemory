if (localStorage.getItem("token") == null) {
    alert("로그인 후 진행해주세요");
    location.href = `/login`;
} else {
    // 페이지를 볼 권한이 없으면 login페이지로
    $.ajax({
        url: `/api/users/isValid`,
        type: "GET",
        headers: { 'X-AUTH-TOKEN': localStorage.getItem("token") },
        success: function (data) {
            if (data.status != 200) {
                alert("인증 토큰이 만료되었습니다. 다시 로그인 해 주세요");
                location.href = `/login`;
            }
        }
    });
}