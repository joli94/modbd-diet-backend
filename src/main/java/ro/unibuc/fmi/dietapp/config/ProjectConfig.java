package ro.unibuc.fmi.dietapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.unibuc.fmi.dietapp.mapper.*;

@Configuration
public class ProjectConfig {

    @Bean
    public AccountMapper accountMapper() {
        return new AccountMapperImpl();
    }

    @Bean
    public BillingMapper billingMapper() {
        return new BillingMapperImpl();
    }

    @Bean
    public CityMapper cityMapper() {
        return new CityMapperImpl();
    }

    @Bean
    public CountryMapper countryMapper() {
        return new CountryMapperImpl();
    }


    @Bean
    public DietGoalMapper diet_goalMapper() {
        return new DietGoalMapperImpl();
    }

    @Bean
    public DietPlanMapper diet_planMapper() {
        return new DietPlanMapperImpl();
    }

    @Bean
    public DietTypeMapper diet_typeMapper() {
        return new DietTypeMapperImpl();
    }

    @Bean
    public DietMapper dietMapper() {
        return new DietMapperImpl();
    }

    @Bean
    public FoodMapper foodMapper() {
        return new FoodMapperImpl();
    }

    @Bean
    public FoodCategoryMapper foodCategoryMapper() {
        return new FoodCategoryMapperImpl();
    }

    @Bean
    public HappinessMapper happinessMapper() {
        return new HappinessMapperImpl();
    }

    @Bean
    public IngredientMapper ingredientMapper() {
        return new IngredientMapperImpl();
    }

    @Bean
    public Optimum_caloriesMapper optimum_caloriesMapper() {
        return new Optimum_caloriesMapperImpl();
    }

    @Bean
    public PaymentMapper paymentMapper() {
        return new PaymentMapperImpl();
    }

    @Bean
    public RegistrationMapper registrationMapper() {
        return new RegistrationMapperImpl();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public WeightMapper weightMapper() {
        return new WeightMapperImpl();
    }
}
