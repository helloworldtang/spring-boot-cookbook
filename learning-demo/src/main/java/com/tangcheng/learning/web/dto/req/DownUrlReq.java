/**
 * @Auther: cheng.tang
 * @Date: 2019/2/11
 * @Description:
 */
package com.tangcheng.learning.web.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * @Auther: cheng.tang
 * @Date: 2019/2/11
 * @Description:
 */
@Data
public class DownUrlReq {

    @ApiModelProperty("需要下载的url")
    @URL
    @NotEmpty
    private String url;

}
