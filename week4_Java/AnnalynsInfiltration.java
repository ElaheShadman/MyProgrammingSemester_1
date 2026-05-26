class AnnalynsInfiltration {
    public static boolean canFastAttack(boolean knightIsAwake) {
          return !knightIsAwake;
    }
 public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
        return knightIsAwake || archerIsAwake || prisonerIsAwake;
    }
