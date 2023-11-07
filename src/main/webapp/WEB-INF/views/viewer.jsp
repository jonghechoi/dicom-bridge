<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/style/viewergrid.css">
</head>
<body>
<div id="viewer">
    <header>
        <img src="/images/KakaoTalk_20231106_132402681-removebg-preview.png"  alt="DicomBridge">
    </header>
    <nav>
        <ul>
            <li><img src="/images/user.png"  alt="Adminstrato"> <div>Adminstrato</div></li>
            <li><img src="/images/add-image.png"  alt="thumbnail"> <div>thumbnail</div></li>
            <li><img src="/images/tools.png"  alt="Toolbar"> <div>Toolbar</div></li>
            <li><img src="/images/report.png"  alt="Report"> <div>Report</div></li>
        </ul>
    </nav>
    <aside>
        <ul>
            <li>
                <img src="/images/worklist.0c26b996e226a3db09e77ef62d440241.png" alt="List">
                <div>List</div>
            </li>
            <li>
                <img src="/images/previous_study.3cb78eecd6d2385b44cb9176ba1fc87c.png" alt="이전">
                <div>이전</div>
            </li>
            <li>
                <img src="/images/next_study.09fbf5daceba6ace2519e74bde2e8420.png" alt="다음">
                <div>다음</div>
            </li>
            <li>
                <img src="/images/default.fa9b027b98a164fb3b5849c0d3ca39ca.png" alt="DefaultTool">
                <div>Default Tool</div>
            </li>
            <li>
                <img src="/images/wwwc.1cc5a0ecda9fd93a085688cedaa8a78b.png" alt="windowLevel">
                <div>윈도우 레벨</div>
            </li>
            <li>
                <img src="/images/invert.ede51ece1c3d447e625c3191b6a2af9c.png" alt="blackAndWhite">
                <div>흑백 반전</div>
            </li>
            <li>
                <img src="/images/pan.47e8cd9f65cf64c8f2fb3d08c6f205ab.png" alt="이동">
                <div>이동</div>
            </li>
            <li>
                <img src="/images/scrollloop.5508766fa02ed78f41fbf1381d8329e4.png" alt="스크롤">
                <div>스크롤 루프</div>
            </li>
            <li>
                <img src="/images/changeImageLayout.2294818a3aa0403736162eb1a10a89b7.png" alt="1시리즈">
                <div>1시리즈</div>
            </li>
            <li>
                <img src="/images/comparison.07c6226e96a236664e9ac4c5ff078c44.png" alt="비교검사">
                <div>비교검사</div>
            </li>
            <li>
                <img src="/images/play.6f437ab2591fe6a6c319e7e77f01df3e.png" alt="clip">
                <div>플레이 클립</div>
            </li>
            <li>
                <img src="/images/tools.2d1068915b14d4ae8a087ca1036b65b2.png" alt=" tool">
                <div>도구</div>
            </li>
            <li>
                <img src="/images/annotation.19ee74cd3ecff2134a423009b58463aa.png" alt="주석">
                <div>주석</div>
            </li>
            <li>
                <img src="/images/refresh.6a8fba2767a97749fd00e3e6f59935f3.png" alt="재설정">
                <div>재설정</div>
            </li>
            <li>
                <img src="/images/changeSeriesLayout.6c2935a8c5a52c722e1055e79e316d58.png" alt="Series">
                <div>Series</div>
            </li>
            <li>
                <img src="/images/changeSeriesLayout.6c2935a8c5a52c722e1055e79e316d58.png" alt="Layout">
                <div>이미지레이아웃</div>
            </li>
        </ul>
    </aside>

    <section id="thumbnail-container">
        <table>
            <thead><tr><th>썸네일</th></tr></thead>
            <tbody></tbody>
        </table>
    </section>
    <section id="image-container"></section>
</div>
</body>
</html>