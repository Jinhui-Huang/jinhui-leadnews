package com.myhd.jinhuileadnewswemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmNewsMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: WmNewsMaterialMapper
 * <br></br>
 * className: WmNewsMaterialMapper
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.mapper
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:59
 */
@Mapper
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {

    /**
     * Description: saveRelations 保存素材和文章的对应关系
     * @return void
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    void saveRelations(@Param("materialIds")List<Integer> materialId, @Param("newsId") Integer newsId, @Param("type") Short type);
}
