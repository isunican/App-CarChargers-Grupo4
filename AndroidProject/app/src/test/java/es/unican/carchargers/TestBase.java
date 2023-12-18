package es.unican.carchargers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.unican.carchargers.activities.main.IMainContract;
import es.unican.carchargers.activities.main.MainPresenter;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public abstract class TestBase {
    @Mock
    protected IMainContract.View mockView;
    @Mock
    protected IRepository mockRepository;
    protected MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter();

        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            //callback.onSuccess(TestUtils.createTestChargers());
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);
    }

}