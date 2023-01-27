//package issueabroad.first.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class ReplyRepositoryTest {
//
//    @Autowired
//    private ReplyRepository replyRepository;
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    @Test
//    public void tt() {
//        System.out.println(articleRepository.findAll());
//    }
//
////    @Test
////    public void insertReply() {
////        IntStream.rangeClosed(1, 300).forEach(i -> {
////            Long id = (long)(Math.random() * 100) + 1;
//////            Article article = Article.builder().ano(id).build();
////            Optional<Article> article = articleRepository.findById(id);
////
////            UserReply userReply = UserReply.builder()
////                    .text("Reply" + i)
////                    .article(article.get())
////                    .replyer("anonymous")
////                    .build();
////
////            replyRepository.save(userReply);
////        });
////    }
//
//}