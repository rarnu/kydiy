package com.rarnu.opendiy.image

import com.rarnu.opendiy.*

object ImageResource {

    val board = this::class.java.getResource("/module/board.png")
    val parallelRare = this::class.java.getResource("/module/mask.png")
    val pendulumParallelRare = this::class.java.getResource("/module/mask_pendulum.png")

    val cardBackground = mapOf(
        CardType.Monster to this::class.java.getResource("/module/board.png"),
        CardType.Magic to this::class.java.getResource("/module/magic.png"),
        CardType.Trap to this::class.java.getResource("/module/trap.png")
    )

    val monsterTypeImage = mapOf(
        MonsterType.Normal to this::class.java.getResource("/module/monster_normal.png"),
        MonsterType.Effect to this::class.java.getResource("/module/monster_effect.png"),
        MonsterType.Ritual to this::class.java.getResource("/module/monster_ritual.png"),
        MonsterType.Fusion to this::class.java.getResource("/module/monster_fusion.png"),
        MonsterType.Synchro to this::class.java.getResource("/module/monster_sync.png"),
        MonsterType.Xyz to this::class.java.getResource("/module/monster_xyz.png"),
        MonsterType.Link to this::class.java.getResource("/module/monster_link.png"),
        MonsterType.Token to this::class.java.getResource("/module/monster_token.png")
    )

    val pendulumTypeImage = mapOf(
        PendulumType.None to this::class.java.getResource("/module/board.png"),
        PendulumType.Normal to this::class.java.getResource("/module/monster_pendulum_normal.png"),
        PendulumType.Effect to this::class.java.getResource("/module/monster_pendulum_effect.png"),
        PendulumType.Ritual to this::class.java.getResource("/module/monster_pendulum_ritual.png"),
        PendulumType.Fusion to this::class.java.getResource("/module/monster_pendulum_fusion.png"),
        PendulumType.Synchro to this::class.java.getResource("/module/monster_pendulum_sync.png"),
        PendulumType.Xyz to this::class.java.getResource("/module/monster_pendulum_xyz.png"),
    )

    val magicTypeImage = mapOf(
        MagicType.Normal to this::class.java.getResource("/module/magic_normal.png"),
        MagicType.Continuous to this::class.java.getResource("/module/magic_cont.png"),
        MagicType.Equip to this::class.java.getResource("/module/magic_equip.png"),
        MagicType.Field to this::class.java.getResource("/module/magic_field.png"),
        MagicType.QuickPlay to this::class.java.getResource("/module/magic_quick.png"),
        MagicType.Ritual to this::class.java.getResource("/module/magic_ritual.png")
    )

    val monsterAttributeImage = mapOf(
        MonsterAttribute.God to this::class.java.getResource("/module/attr_god.png"),
        MonsterAttribute.Earth to this::class.java.getResource("/module/attr_earth.png"),
        MonsterAttribute.Dark to this::class.java.getResource("/module/attr_dark.png"),
        MonsterAttribute.Fire to this::class.java.getResource("/module/attr_fire.png"),
        MonsterAttribute.Light to this::class.java.getResource("/module/attr_light.png"),
        MonsterAttribute.Water to this::class.java.getResource("/module/attr_water.png"),
        MonsterAttribute.Wind to this::class.java.getResource("/module/attr_wind.png"),
    )

    val levelImage = mapOf(
        0 to this::class.java.getResource("/module/empty.png"),
        1 to this::class.java.getResource("/module/level_1.png"),
        2 to this::class.java.getResource("/module/level_2.png"),
        3 to this::class.java.getResource("/module/level_3.png"),
        4 to this::class.java.getResource("/module/level_4.png"),
        5 to this::class.java.getResource("/module/level_5.png"),
        6 to this::class.java.getResource("/module/level_6.png"),
        7 to this::class.java.getResource("/module/level_7.png"),
        8 to this::class.java.getResource("/module/level_8.png"),
        9 to this::class.java.getResource("/module/level_9.png"),
        10 to this::class.java.getResource("/module/level_10.png"),
        11 to this::class.java.getResource("/module/level_11.png"),
        12 to this::class.java.getResource("/module/level_12.png")
    )

    val rankImage = mapOf(
        0 to this::class.java.getResource("/module/empty.png"),
        1 to this::class.java.getResource("/module/rank_1.png"),
        2 to this::class.java.getResource("/module/rank_2.png"),
        3 to this::class.java.getResource("/module/rank_3.png"),
        4 to this::class.java.getResource("/module/rank_4.png"),
        5 to this::class.java.getResource("/module/rank_5.png"),
        6 to this::class.java.getResource("/module/rank_6.png"),
        7 to this::class.java.getResource("/module/rank_7.png"),
        8 to this::class.java.getResource("/module/rank_8.png"),
        9 to this::class.java.getResource("/module/rank_9.png"),
        10 to this::class.java.getResource("/module/rank_10.png"),
        11 to this::class.java.getResource("/module/rank_11.png"),
        12 to this::class.java.getResource("/module/rank_12.png")
    )

    val trapTypeImage = mapOf(
        TrapType.Normal to this::class.java.getResource("/module/trap_normal.png"),
        TrapType.Continuous to this::class.java.getResource("/module/trap_cont.png"),
        TrapType.Counter to this::class.java.getResource("/module/trap_counter.png"),
    )

    val licImage = mapOf(
        LicType.lt1 to this::class.java.getResource("/module/licmark_1.png"),
        LicType.lt2 to this::class.java.getResource("/module/licmark_2.png"),
        LicType.lt3 to this::class.java.getResource("/module/licmark_3.png"),
        LicType.lt4 to this::class.java.getResource("/module/licmark_4.png"),
        LicType.lt5 to this::class.java.getResource("/module/licmark_5.png")
    )

    val linkTypeImage = mapOf(
        LinkPosition.lp1 to this::class.java.getResource("/module/link_mark_1.png"),
        LinkPosition.lp2 to this::class.java.getResource("/module/link_mark_2.png"),
        LinkPosition.lp3 to this::class.java.getResource("/module/link_mark_3.png"),
        LinkPosition.lp4 to this::class.java.getResource("/module/link_mark_4.png"),
        LinkPosition.lp6 to this::class.java.getResource("/module/link_mark_6.png"),
        LinkPosition.lp7 to this::class.java.getResource("/module/link_mark_7.png"),
        LinkPosition.lp8 to this::class.java.getResource("/module/link_mark_8.png"),
        LinkPosition.lp9 to this::class.java.getResource("/module/link_mark_9.png"),
    )

}

