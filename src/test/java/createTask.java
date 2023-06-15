import com.example.tasker.TaskerApplication;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.task.service.TaskService;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = TaskerApplication.class)
@Transactional
@RequiredArgsConstructor
public class createTask {

    final TaskService taskService;
    final TaskRepository taskRepository;
    final UserRepository userRepository;

    @Test
    public void 테스크생성() throws Exception {
        //given
        User user = User.builder()
                .phoneNumber("010-000-000")
                .build();
        User saved = userRepository.save(user);

        //when
        PostTaskRes test = taskService.createTask(saved.getUserId(), new PostTaskReq("test"), "2023-06-01");

        //then
        Assertions.assertThat(test.getTitle()).isEqualTo("test");

    }


}
