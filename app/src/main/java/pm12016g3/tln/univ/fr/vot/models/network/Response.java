package pm12016g3.tln.univ.fr.vot.models.network;

import lombok.Data;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.models.network.
 * File Response.java.
 * Created by Ysee on 01/06/2017 - 10:28.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
@Data
public class Response<T>  {
    private int httpErrorCode;
    private String errorMessage;
    private boolean success;
    private T data;
}
