package com.zum.study.template;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Joeylee on 2017-09-12.
 */
public interface BufferedReaderCallback {

    Integer doSomethingWithReader(BufferedReader reader) throws IOException;
}
