package org.example.examplefabricmod.item.CustomItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EightBallItem extends Item {

    public EightBallItem(Settings settings) {
        super(settings);
    }

    // “use”方法当玩家使用它时被调用
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        // 判定是否为服务端以及使用的是否为右手
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            // 输出随机数字
            outputRandomNumber(user);
            // 设置一个使用CD，20刻为1秒
            user.getItemCooldownManager().set(this, 20);
        }
        return super.use(world, user, hand);
    }

    // 输出获得的随机数
    public void outputRandomNumber(Entity entity) {
        // 使用实体（entity）的自带方法进行文字的输出
        entity.sendMessage(Text.literal("Your random number is " + getRandomNumber()));
    }

    // 获得随机数
    public int getRandomNumber() {
        // 使用MC的随机数生成方法来生成随机数
        return Random.create().nextInt(10);
    }
}
