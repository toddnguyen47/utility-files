use super::icar::ICar;

pub struct Mazda;

impl Mazda {
    #[allow(dead_code)]
    pub fn new() -> Self {
        Self
    }
}

impl ICar for Mazda {
    fn get_make(&self) -> String {
        "Mazda".to_string()
    }
}
