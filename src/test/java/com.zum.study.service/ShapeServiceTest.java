package com.zum.study.service;

import com.zum.study.domain.Shape;
import com.zum.study.domain.impl.Square;
import com.zum.study.repository.ShapeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import sun.plugin.dom.exception.InvalidStateException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;



/**
 * Created by Joeylee on 2017-08-11.
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/context/application-context.xml"})
public class ShapeServiceTest {

    @InjectMocks
    private ShapeService shapeService;

    @Mock
    private ShapeRepository shapeRepository;

    private int point;

    @Before
    public void setup() {
        this.point =10;

        doReturn(new Shape(this.point)).when(shapeRepository).generate(any(Integer.class));
        doReturn(new Square()).when(shapeRepository).getSquare();

        doThrow(new InvalidStateException("도형에 필요한 점은 최소 3개 이상입니다.")).when(shapeRepository).generate(2);
    }

    @Test
    public void shouldReturnShapeWithParam() throws Exception {

        //given
        int point =10;


        //when
        Shape shape = shapeService.get(point, false);

        //then
        assertThat(shape, not(nullValue()));
        assertThat(shape.getClass().getSimpleName(), is("Shape"));
        assertThat(shape.getPoint(), is(10));

        verify(shapeRepository, times(1)).generate(any(Integer.class));

    }

    @Test
    public void shouldReturnSquare() throws Exception {
        //given
        int point =4;
        boolean hasEqualAdjacentSides = true;

        //when
        Shape shape = shapeService.get(point, hasEqualAdjacentSides);

        //then

        assertThat(shape, not(nullValue()));
        assertThat(shape.getClass().getSimpleName(), is("Square"));

        verify(shapeRepository, times(1)).getSquare();
    }

    @Test(expected = InvalidStateException.class)
    public void shouldThrowErrorWithParamUnder3() throws Exception {
        //given
        int param =2;
        //when
        Shape shape = shapeService.get(param, false);

        //then ... throw error
        
    }

}
